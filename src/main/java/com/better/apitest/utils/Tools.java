package com.better.apitest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.better.apitest.domain.Environment;
import com.better.apitest.domain.Request;
import com.better.apitest.dto.*;
import com.better.apitest.dto.req.SaveEnvironmentReq;
import com.better.apitest.dto.resp.EnvironmentResp;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/22 12:14 下午
 * @Description:
 */
public class Tools {


    /**
     * Environment对象转换工具：领域模型转Resp对象
     * @param environmentList
     * @param environmentRespList
     * @return EnvironmentResp
     */
    public static List<EnvironmentResp> environmentListToEnvironmentRespList(List<Environment> environmentList, List<EnvironmentResp> environmentRespList) {
        //            Map<Long, Environment> environmentMap = environmentList.stream().collect(Collectors.toMap(Environment::getId, x -> x, (a, b) -> b));
//            environmentRespList.forEach(environmentResp -> {
//                Long id = environmentResp.getId();
//                Environment environment = environmentMap.get(id);
//                if (!Objects.isNull(environment)) {
//                    String apiHeaders = environment.getApiHeaders();
//                    String apiUrlParameter = environment.getApiUrlParameter();
//                    String initVariables = environment.getInitVariables();
//                    String variablesByFunc = environment.getVariablesByFunc();
//                    environmentResp.setApiHeaders(JSONObject.parseArray(apiHeaders, HeaderDto.class));
//                    environmentResp.setApiUrlParameter(JSONObject.parseArray(apiUrlParameter, UrlParameterDto.class));
//                    environmentResp.setInitVariables(JSONObject.parseArray(initVariables, VariableDto.class));
//                    environmentResp.setVariablesByFunc(JSONObject.parseArray(variablesByFunc, FuncVariableDto.class));
//                }
//            });
        for (int i = 0; i < environmentRespList.size(); i++) {
            environmentRespList.get(i).setApiHeaderList(JSONObject.parseArray(environmentList.get(i).getApiHeaders(), HeaderDto.class));
            environmentRespList.get(i).setApiUrlParameterList(JSONObject.parseArray(environmentList.get(i).getApiUrlParameter(), UrlParameterDto.class));
            environmentRespList.get(i).setInitVariableList(JSONObject.parseArray(environmentList.get(i).getInitVariables(), VariableDto.class));
            environmentRespList.get(i).setVariablesByFuncList(JSONObject.parseArray(environmentList.get(i).getVariablesByFunc(), FuncVariableDto.class));
        }
        return environmentRespList;
    }

    /**
     * Environment对象转换工具：Req对象转领域模型
     * @param environmentList
     * @param saveEnvironmentReqList
     * @return Environment
     */
    public static List<Environment> saveEnvironmentReqListToEnvironmentList(List<SaveEnvironmentReq> saveEnvironmentReqList, List<Environment> environmentList) {
        for (int i = 0; i < environmentList.size(); i++) {
            environmentList.get(i).setApiHeaders(JSON.toJSONString(saveEnvironmentReqList.get(i).getApiHeaderList()));
            environmentList.get(i).setApiUrlParameter(JSON.toJSONString(saveEnvironmentReqList.get(i).getApiUrlParameterList()));
            environmentList.get(i).setInitVariables(JSON.toJSONString(saveEnvironmentReqList.get(i).getInitVariableList()));
            environmentList.get(i).setVariablesByFunc(JSON.toJSONString(saveEnvironmentReqList.get(i).getVariablesByFuncList()));
        }
        return environmentList;
    }

    /**
     * Request对象转换工具：Req对象转领域模型
     * @param requestList
     * @param requestDtoList
     * @return Request
     */
    public static List<Request> requestDtoToRequest(List<RequestDto> requestDtoList, List<Request> requestList) {
        for (int i = 0; i < requestList.size(); i++) {
            requestList.get(i).setApiHeaders(JSON.toJSONString(requestDtoList.get(i).getApiHeadersList()));
            requestList.get(i).setApiUrlParameter(JSON.toJSONString(requestDtoList.get(i).getApiUrlParameterList()));
            requestList.get(i).setRequestBody(JSON.toJSONString(requestDtoList.get(i).getApiBody()));
            requestList.get(i).setApiVariables(JSON.toJSONString(requestDtoList.get(i).getApiVariableList()));
            requestList.get(i).setApiAssertions(JSON.toJSONString(requestDtoList.get(i).getApiAssertionList()));
        }
        return requestList;
    }

    public static List<RequestDto> requestToRequestDto(List<Request> requestList, List<RequestDto> requestDtoList) {
        for (int i = 0; i < requestList.size(); i++) {
            requestDtoList.get(i).setApiAssertionList(JSONObject.parseArray(requestList.get(i).getApiAssertions(),AssertionDto.class));
            requestDtoList.get(i).setApiUrlParameterList(JSONObject.parseArray(requestList.get(i).getApiUrlParameter(), UrlParameterDto.class));
            requestDtoList.get(i).setApiBody(JSONObject.parseObject(requestList.get(i).getRequestBody(), ApiBodyDto.class));
            requestDtoList.get(i).setApiVariableList(JSONObject.parseArray(requestList.get(i).getApiVariables(), ApiVariableDto.class));
            requestDtoList.get(i).setApiHeadersList(JSONObject.parseArray(requestList.get(i).getApiHeaders(), HeaderDto.class));
        }
        return requestDtoList;
    }
}
