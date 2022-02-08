package com.better.apitest.dto;

import java.util.Date;
import java.util.List;

public class EnvironmentDto {
    private Long id;

    private Long projectId;

    private String environmentName;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private Boolean delFlag;

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

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public List<HeaderDto> getApiHeaderList() {
        return apiHeaderList;
    }

    public void setApiHeaderList(List<HeaderDto> apiHeaderList) {
        this.apiHeaderList = apiHeaderList;
    }

    public List<UrlParameterDto> getApiUrlParameterList() {
        return apiUrlParameterList;
    }

    public void setApiUrlParameterList(List<UrlParameterDto> apiUrlParameterList) {
        this.apiUrlParameterList = apiUrlParameterList;
    }

    public List<VariableDto> getInitVariableList() {
        return initVariableList;
    }

    public void setInitVariableList(List<VariableDto> initVariableList) {
        this.initVariableList = initVariableList;
    }

    public List<FuncVariableDto> getVariablesByFuncList() {
        return variablesByFuncList;
    }

    public void setVariablesByFuncList(List<FuncVariableDto> variablesByFuncList) {
        this.variablesByFuncList = variablesByFuncList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectId=").append(projectId);
        sb.append(", environmentName=").append(environmentName);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", delFlag=").append(delFlag);
        sb.append("]");
        return sb.toString();
    }
}