package com.better.apitest.service.impl;

import com.better.apitest.domain.Project;
import com.better.apitest.mapper.ProjectMapper;
import com.better.apitest.service.ProjectService;
import com.better.apitest.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceImp.class);
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public List<Project> getProjectList() {
        return projectMapper.selectAllList();
    }

    @Override
    public void saveProject(Project project) {
        project.setId(snowFlake.nextId());
        projectMapper.insertSelective(project);
    }

    @Override
    public Boolean removeProject(Project project) {
        project.setDelFlag(true);
        return projectMapper.updateByPrimaryKeySelective(project) > 0;
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }
}
