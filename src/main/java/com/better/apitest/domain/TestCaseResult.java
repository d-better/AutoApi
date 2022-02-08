package com.better.apitest.domain;

import java.util.Date;

public class TestCaseResult {
    private Long id;

    private Long testCaseId;

    private Long collectionResultId;

    private Long environmentId;

    private Long runBy;

    private Date createDate;

    private Boolean success;

    private Integer totalAssertions;

    private Integer successAssertions;

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

    public Long getCollectionResultId() {
        return collectionResultId;
    }

    public void setCollectionResultId(Long collectionResultId) {
        this.collectionResultId = collectionResultId;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    public Long getRunBy() {
        return runBy;
    }

    public void setRunBy(Long runBy) {
        this.runBy = runBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getTotalAssertions() {
        return totalAssertions;
    }

    public void setTotalAssertions(Integer totalAssertions) {
        this.totalAssertions = totalAssertions;
    }

    public Integer getSuccessAssertions() {
        return successAssertions;
    }

    public void setSuccessAssertions(Integer successAssertions) {
        this.successAssertions = successAssertions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", testCaseId=").append(testCaseId);
        sb.append(", collectionResultId=").append(collectionResultId);
        sb.append(", environmentId=").append(environmentId);
        sb.append(", runBy=").append(runBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", success=").append(success);
        sb.append(", totalAssertions=").append(totalAssertions);
        sb.append(", successAssertions=").append(successAssertions);
        sb.append("]");
        return sb.toString();
    }
}