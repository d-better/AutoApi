package com.better.apitest.dto.req;

import java.io.Serializable;

public class ProjectSaveReq implements Serializable {

    /**
     * 项目ID；新建项目是不传，修改项目时要传
     */
    private Long id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目类型
     */
    private Integer projectType;

    /**
     * 关联用户
     */
    private Long authUsersId;

    /**
     * 描述信息
     */
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public Long getAuthUsersId() {
        return authUsersId;
    }

    public void setAuthUsersId(Long authUsersId) {
        this.authUsersId = authUsersId;
    }
}
