package com.better.apitest.domain;

import java.util.Date;

public class Request {
    private Long id;

    private Long testCaseId;
    /**
     * 请求类型1 sql,2 http 3 redis
     */
    private Integer mainType;

    private Long dbConfigId;

    private String requestName;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 接口地址
     */
    private String requestPath;

    /**
     * 排序值
     */
    private Integer requestOrder;
    /**
     * 接口执行等待时间
     */
    private Integer sleepTime;

    private Date createDate;

    private Date updateDate;

    /**
     * sql
     */
    private String sqlStr;
    /**
     * 请求体
     */
    private String requestBody;
    /**
     * 断言信息
     */
    private String apiAssertions;
    /**
     * url拼接参数
     */
    private String apiUrlParameter;
    /**
     * 变量信息
     */
    private String apiVariables;

    /**
     * 请求头
     */
    private String apiHeaders;
    /**
     * 删除标记
     */
    private Boolean delFlag;

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getApiAssertions() {
        return apiAssertions;
    }

    public void setApiAssertions(String apiAssertions) {
        this.apiAssertions = apiAssertions;
    }

    public String getApiUrlParameter() {
        return apiUrlParameter;
    }

    public void setApiUrlParameter(String apiUrlParameter) {
        this.apiUrlParameter = apiUrlParameter;
    }

    public String getApiVariables() {
        return apiVariables;
    }

    public void setApiVariables(String apiVariables) {
        this.apiVariables = apiVariables;
    }

    public String getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(String apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", mainType=").append(mainType);
        sb.append(", dbConfigId=").append(dbConfigId);
        sb.append(", requestName=").append(requestName);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", requestPath=").append(requestPath);
        sb.append(", requestOrder=").append(requestOrder);
        sb.append(", sleepTime=").append(sleepTime);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", sqlStr=").append(sqlStr);
        sb.append(", requestBody=").append(requestBody);
        sb.append(", apiAssertions=").append(apiAssertions);
        sb.append(", apiUrlParameter=").append(apiUrlParameter);
        sb.append(", apiVariables=").append(apiVariables);
        sb.append(", apiHeaders=").append(apiHeaders);
        sb.append("]");
        return sb.toString();
    }
}