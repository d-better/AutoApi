package com.better.apitest.dto;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/25 12:14 下午
 * @Description:
 */
public class RequestDto {

    private Long id;

    private Integer mainType;

    private Long dbConfigId;

    private String requestName;

    private String requestMethod;

    private String requestPath;

    private Integer requestOrder;

    private Integer sleepTime;

    private String sqlStr;

    private List<AssertionDto> apiAssertionList;

    private List<UrlParameterDto> apiUrlParameterList;

    private List<ApiVariableDto> apiVariableList;

    private List<HeaderDto> apiHeadersList;

    private ApiBodyDto apiBody;

    public ApiBodyDto getApiBody() {
        return apiBody;
    }

    public void setApiBody(ApiBodyDto apiBody) {
        this.apiBody = apiBody;
    }

    public List<ApiVariableDto> getApiVariableList() {
        return apiVariableList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRequestOrder() {
        return requestOrder;
    }

    public void setRequestOrder(Integer requestOrder) {
        this.requestOrder = requestOrder;
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
        sb.append(", id=").append(id);
        sb.append(", mainType=").append(mainType);
        sb.append(", dbConfigId=").append(dbConfigId);
        sb.append(", requestName=").append(requestName);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", requestPath=").append(requestPath);
        sb.append(", requestOrder=").append(requestOrder);
        sb.append(", sleepTime=").append(sleepTime);
        sb.append(", sqlStr=").append(sqlStr);
        sb.append("]");
        return sb.toString();
    }
}

