package com.better.apitest.dto;

import com.better.apitest.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/25 12:14 下午
 * @Description: 最终的请求类
 */

public class RunRequestDto {

    private RequestDto requestDto;

    private Integer mainType;

    private Long dbConfigId;

    private String requestName;

    private String requestMethod;

    private String requestPath;

    private Integer sleepTime;

    private String sqlStr;

    private Map<String, String> variableMap;

    private List<AssertionDto> apiAssertionList;

    private List<UrlParameterDto> apiUrlParameterList;

    private List<ApiVariableDto> apiVariableList;

    private List<HeaderDto> apiHeadersList;

    private List<FormDataDto> formDataDtoList;

    private RawDto rawDto;

    private EnvironmentDto environmentDto;

    private final Logger LOG = LoggerFactory.getLogger(RunRequestDto.class);

    /**
     * 有参构造
     *
     * @param requestDto
     * @param environmentDto
     */

    public RunRequestDto(RequestDto requestDto, EnvironmentDto environmentDto) {
        this.requestDto = requestDto;
        this.mainType = requestDto.getMainType();
        this.dbConfigId = requestDto.getDbConfigId();
        this.requestName = requestDto.getRequestName();
        this.requestMethod = requestDto.getRequestMethod();
        this.requestPath = requestDto.getRequestPath();
        this.sleepTime = requestDto.getSleepTime();
        this.sqlStr = requestDto.getSqlStr();
        this.environmentDto = environmentDto;
        if (ObjectUtils.isEmpty(environmentDto)) {
            this.environmentDto = new EnvironmentDto();
        } else {
//            处理自定义变量和自定义方法
            List<VariableDto> variableDtoList = this.environmentDto.getInitVariableList();
            List<FuncVariableDto> funcVariableDtoList = this.environmentDto.getVariablesByFuncList();
            for (int i = 0; i < funcVariableDtoList.size(); i++) {
                String value = getValueByFunc(funcVariableDtoList.get(i));
                variableDtoList.add(new VariableDto(funcVariableDtoList.get(i).getName(), value));
            }
        }
        this.variableMap = variableToMap(this.environmentDto.getInitVariableList());
//        处理url请求参数
        apiUrlParameterList = new ArrayList<>();
        List<UrlParameterDto> urlParameterDtoList = this.requestDto.getApiUrlParameterList();
        if (!ObjectUtils.isEmpty(this.environmentDto.getApiUrlParameterList())) {
            urlParameterDtoList.addAll(this.environmentDto.getApiUrlParameterList());
        }
        urlParameterDtoList.stream()
                .filter(x -> !x.getKey().equals("") ||
                        !x.getValue().equals(""))
                .forEach(x -> {
                    apiUrlParameterList.add(new UrlParameterDto(preProcess(x.getKey()), preProcess(x.getValue())));
                });
//        处理FormData请求体参数
        formDataDtoList = new ArrayList<>();
        List<FormDataDto> formDataDtoList = this.requestDto.getApiBody().getFormData();
        formDataDtoList.stream()
                .filter(x -> !x.getKey().equals("") ||
                        !x.getValue().equals(""))
                .forEach(x -> {
                    formDataDtoList.add(new FormDataDto(preProcess(x.getKey()), preProcess(x.getValue())));
                });
//        处理json请求体参数
        rawDto = new RawDto();
        rawDto.setKey(this.requestDto.getApiBody().getRaw().getKey());
        rawDto.setValue(preProcess(this.requestDto.getApiBody().getRaw().getValue()));
//        处理header信息
        apiHeadersList = new ArrayList<>();
        List<HeaderDto> headerDtoList = this.requestDto.getApiHeadersList();
        if (!ObjectUtils.isEmpty(this.environmentDto.getApiHeaderList())) {
            List<HeaderDto> tempHeaders = new ArrayList<>();
            for (int i = 0; i < this.environmentDto.getApiHeaderList().size(); i++) {
                tempHeaders.add(new HeaderDto(this.environmentDto.getApiHeaderList().get(i).getKey(), this.environmentDto.getApiHeaderList().get(i).getValue()));
            }
            headerDtoList.addAll(tempHeaders);
        }
        headerDtoList.stream()
                .filter(x -> !x.getKey().equals("") ||
                        !x.getValue().equals(""))
                .forEach(x -> {
                    if (x.getKey().equals("encryptValue")) {
                        apiHeadersList.add(new HeaderDto(preProcess(x.getKey()), getSign(preProcess(x.getValue()))));
                    } else {
                        apiHeadersList.add(new HeaderDto(preProcess(x.getKey()), preProcess(x.getValue())));
                    }
                });
    }

    /**
     * 封装加签方法
     * @param encryptValue
     * @return
     */
    private String getSign(String encryptValue) {
        if (MD5.isMD5(encryptValue)) {
            LOG.info("encryptValue no Sign: {}", encryptValue);
            return encryptValue;
        }
//        如果是get请求，需要就从url里取到请求参数并且拼接成 name=111&code=222&age=333的参数形式
        StringJoiner md5Value = new StringJoiner("&");
        if (this.requestMethod.toLowerCase().equals("get")) {
            LOG.info("get请求加签，开始拼接参数");
            if (this.apiUrlParameterList.size() > 0) {
                for (UrlParameterDto urlParameterDto : this.apiUrlParameterList) {
                    md5Value.add(urlParameterDto.getKey() + "=" + urlParameterDto.getValue());
                }
            }
            LOG.info("参数拼接完成{}", md5Value.toString());
        } else {
//            除了get就是post，不考虑其他请求方式
           md5Value.add(this.rawDto.getValue());
           LOG.info("post请求加签,获取到请求参数{}",md5Value.toString());
        }
        String sign = MD5.encrytMD5(MD5.encrytMD5(md5Value.toString()) + MD5.encrytMD5(encryptValue));
        LOG.info("encryptValue Sign: {}", sign);
        return sign;
    }


    /**
     * 变量匹配方法
     *
     * @param source
     * @return
     */
    private String preProcess(String source) {
        // 按指定模式在字符串查找
        String sourceCopy = source;
        //\{[^}]*\}
        //String regex = "<[^>]*>";
        String regex = "\\{\\{[^}]*}}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCopy);
        while (matcher.find()) {
            String nameMatch = matcher.group().replaceAll("\\{\\{", "");
            String name = nameMatch.replaceAll("\\}\\}", "");
            String value = "";
            if (variableMap.containsKey(name)) {
                value = (String) variableMap.get(name);
            }
            if (null == value) {
                System.out.println(sourceCopy);
            }
            //增加sql保存变量数组的功能
            name = name.replace("[", "\\[").replace("]", "\\]");
            sourceCopy = sourceCopy.replaceAll("\\{\\{" + name + "}}", value);
        }
        return sourceCopy;
    }

    /**
     * 对象转成Map
     *
     * @param variableDtoList
     * @return
     */
    private Map<String, String> variableToMap(List<VariableDto> variableDtoList) {
        Map<String, String> map = new HashMap<>();
        if (ObjectUtils.isEmpty(variableDtoList)) {
            return map;
        }
        variableDtoList.forEach(x -> {
            map.put(x.getName(), x.getValue());
        });
        return map;
    }

    /**
     * 解析自定义方法
     *
     * @param funcVariableDto
     * @return
     */
    private String getValueByFunc(FuncVariableDto funcVariableDto) {
        String func = funcVariableDto.getFunc();
        try {
            if ("now()".equals(func)) {
//                获取当前的时间戳
                long nowMilli = System.currentTimeMillis();
                if ("加(ms)".equals(funcVariableDto.getParam1())) {
                    return String.valueOf(nowMilli + Math.abs(Long.parseLong(funcVariableDto.getParam2())));
                } else if ("减(ms)".equals(funcVariableDto.getParam1())) {
                    return String.valueOf(nowMilli - Math.abs(Long.parseLong(funcVariableDto.getParam2())));
                } else if ("0点加(ms)".equals(funcVariableDto.getParam1())) {
                    return String.valueOf(nowMilli - (nowMilli + 28800000L) % 86400000L + Math.abs(Long.parseLong(funcVariableDto.getParam2())));
                } else {
                    return String.valueOf(nowMilli - (nowMilli + 28800000L) % 86400000L - Math.abs(Long.parseLong(funcVariableDto.getParam2())));
                }
            } else if ("year()".equals(func)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, Integer.parseInt(funcVariableDto.getParam2()));
                return format.format(calendar.getTime());
            } else if ("month()".equals(func)) {
                SimpleDateFormat format = new SimpleDateFormat("MM");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, Integer.parseInt(funcVariableDto.getParam2()));
                return format.format(calendar.getTime());
            } else if ("date()".equals(func)) {
                SimpleDateFormat format = new SimpleDateFormat("dd");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, Integer.parseInt(funcVariableDto.getParam2()));
                return format.format(calendar.getTime());
            } else if ("hour()".equals(func)) {
                SimpleDateFormat format = new SimpleDateFormat("HH");
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(funcVariableDto.getParam2()));
                return format.format(calendar.getTime());
            } else if ("formatDate()".equals(func)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                if (funcVariableDto.getParam1().equals("天数")) {
                    calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(funcVariableDto.getParam2()));
                } else if (funcVariableDto.getParam1().equals("月数")) {
                    calendar.add(Calendar.MONTH, Integer.parseInt(funcVariableDto.getParam2()));
                } else {
                    calendar.add(Calendar.YEAR, Integer.parseInt(funcVariableDto.getParam2()));
                }
                return format.format(calendar.getTime());
            } else {
                Random random = new Random();
                int param1 = Integer.parseInt(funcVariableDto.getParam1());
                int param2 = Integer.parseInt(funcVariableDto.getParam2());
                return String.valueOf(random.nextInt(param2 - param1 + 1) + param1);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LOG.error("自定义{}方法计算错误", func);
            return "计算错误";
        }
    }


    public EnvironmentDto getEnvironmentDto() {
        return environmentDto;
    }

    public void setEnvironmentDto(EnvironmentDto environmentDto) {
        this.environmentDto = environmentDto;
    }

    public RequestDto getRequestDto() {
        return requestDto;
    }

    public void setRequestDto(RequestDto requestDto) {
        this.requestDto = requestDto;
    }

    public List<FormDataDto> getFormDataDtoList() {
        return formDataDtoList;
    }

    public void setFormDataDtoList(List<FormDataDto> formDataDtoList) {
        this.formDataDtoList = formDataDtoList;
    }

    public RawDto getRawDto() {
        return rawDto;
    }

    public void setRawDto(RawDto rawDto) {
        this.rawDto = rawDto;
    }

    public List<ApiVariableDto> getApiVariableList() {
        return apiVariableList;
    }


    public Integer getMainType() {
        return mainType;
    }

    public void setMainType(Integer mainType) {
        this.mainType = mainType;
    }

    public Long getDbConfigId() {
        return dbConfigId;
    }

    public void setDbConfigId(Long dbConfigId) {
        this.dbConfigId = dbConfigId;
    }

    public String getRequestName() {
        return requestName;
    }

    public Map getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map variableMap) {
        this.variableMap = variableMap;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }


    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }


    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public List<AssertionDto> getApiAssertionList() {
        return apiAssertionList;
    }

    public void setApiAssertionList(List<AssertionDto> apiAssertionList) {
        this.apiAssertionList = apiAssertionList;
    }

    public List<UrlParameterDto> getApiUrlParameterList() {
        return apiUrlParameterList;
    }

    public void setApiUrlParameterList(List<UrlParameterDto> apiUrlParameterList) {
        this.apiUrlParameterList = apiUrlParameterList;
    }

    public void setApiVariableList(List<ApiVariableDto> apiVariableList) {
        this.apiVariableList = apiVariableList;
    }


    public List<HeaderDto> getApiHeadersList() {
        return apiHeadersList;
    }

    public void setApiHeadersList(List<HeaderDto> apiHeadersList) {
        this.apiHeadersList = apiHeadersList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mainType=").append(mainType);
        sb.append(", dbConfigId=").append(dbConfigId);
        sb.append(", requestName=").append(requestName);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", requestPath=").append(requestPath);
        sb.append(", sleepTime=").append(sleepTime);
        sb.append(", sqlStr=").append(sqlStr);
        sb.append("]");
        return sb.toString();
    }
}

