package com.better.apitest.dto.req;

import com.better.apitest.dto.FuncVariableDto;
import com.better.apitest.dto.HeaderDto;
import com.better.apitest.dto.UrlParameterDto;
import com.better.apitest.dto.VariableDto;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 10:31 下午
 * @Description:
 */
public class SaveEnvironmentReq implements Serializable {
    private Long id;

    private Long projectId;

    private String environmentName;

    private List<HeaderDto> apiHeaderList;

    private List<UrlParameterDto> apiUrlParameterList;

    private List<VariableDto> initVariableList;

    private List<FuncVariableDto> variablesByFuncList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public List<HeaderDto> getApiHeaderList() {
        return apiHeaderList;
    }

    public void setApiHeaderList(List<HeaderDto> apiHeaderList) {
        this.apiHeaderList = apiHeaderList;
    }

    public List<VariableDto> getInitVariableList() {
        return initVariableList;
    }

    public void setInitVariableList(List<VariableDto> initVariableList) {
        this.initVariableList = initVariableList;
    }

    public List<UrlParameterDto> getApiUrlParameterList() {
        return apiUrlParameterList;
    }

    public void setApiUrlParameterList(List<UrlParameterDto> apiUrlParameterList) {
        this.apiUrlParameterList = apiUrlParameterList;
    }


    public List<FuncVariableDto> getVariablesByFuncList() {
        return variablesByFuncList;
    }

    public void setVariablesByFuncList(List<FuncVariableDto> variablesByFuncList) {
        this.variablesByFuncList = variablesByFuncList;
    }
}
