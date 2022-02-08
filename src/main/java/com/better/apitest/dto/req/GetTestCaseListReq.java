package com.better.apitest.dto.req;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/22 8:29 下午
 * @Description:
 */
public class GetTestCaseListReq extends BaseQuery {


    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
