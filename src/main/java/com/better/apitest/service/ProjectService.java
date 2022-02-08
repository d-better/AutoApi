package com.better.apitest.service;

import com.better.apitest.domain.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getProjectList();
    void saveProject(Project project);
    void updateProject(Project project);
    Boolean removeProject(Project project);
}
