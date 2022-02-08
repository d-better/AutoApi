package com.better.apitest.dto.resp;

import com.better.apitest.dto.RequestDto;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/8/6 4:56 下午
 * @Description:
 */
public class TestCaseDetailResp {
    private Long id;

    private String testCaseName;

    private String remarks;

    private Integer priority;

    private Long projectId;

    private List<RequestDto> requestDtoList;

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

    public List<RequestDto> getRequestDtoList() {
        return requestDtoList;
    }

    public void setRequestDtoList(List<RequestDto> requestDtoList) {
        this.requestDtoList = requestDtoList;
    }
}
