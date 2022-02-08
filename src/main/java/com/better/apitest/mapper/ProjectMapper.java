package com.better.apitest.mapper;

import com.better.apitest.domain.Project;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    List<Project> selectAllList();

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}