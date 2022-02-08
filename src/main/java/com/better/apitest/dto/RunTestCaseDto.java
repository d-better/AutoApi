package com.better.apitest.dto;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/8/8 9:53 下午
 * @Description:
 */
public class RunTestCaseDto {
    private Long id;

    private String testCaseName;

    private String remarks;

    private Integer priority;

    private Long projectId;

    private Long environmentId;

    private List<RunRequestDto> requestDtoList;

    public List<RunRequestDto> getRequestDtoList() {
        return requestDtoList;
    }

    public void setRequestDtoList(List<RunRequestDto> requestDtoList) {
        this.requestDtoList = requestDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", testCaseName=").append(testCaseName);
        sb.append(", remarks=").append(remarks);
        sb.append(", priority=").append(priority);
        sb.append(", projectId=").append(projectId);
        sb.append(", environmentId=").append(environmentId);
        sb.append("]");
        return sb.toString();
    }

}
