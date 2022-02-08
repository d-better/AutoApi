package com.better.apitest.domain;

import java.util.Date;

public class Environment {
    private Long id;

    private Long projectId;

    private String environmentName;

    private Long createBy;

    private Date createDate;

    private Long updateBy;

    private Date updateDate;

    private Boolean delFlag;

    private String apiHeaders;

    private String apiUrlParameter;

    private String initVariables;

    private String variablesByFunc;

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

    public String getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(String apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public String getApiUrlParameter() {
        return apiUrlParameter;
    }

    public void setApiUrlParameter(String apiUrlParameter) {
        this.apiUrlParameter = apiUrlParameter;
    }

    public String getInitVariables() {
        return initVariables;
    }

    public void setInitVariables(String initVariables) {
        this.initVariables = initVariables;
    }

    public String getVariablesByFunc() {
        return variablesByFunc;
    }

    public void setVariablesByFunc(String variablesByFunc) {
        this.variablesByFunc = variablesByFunc;
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
        sb.append(", apiHeaders=").append(apiHeaders);
        sb.append(", apiUrlParameter=").append(apiUrlParameter);
        sb.append(", initVariables=").append(initVariables);
        sb.append(", variablesByFunc=").append(variablesByFunc);
        sb.append("]");
        return sb.toString();
    }
}