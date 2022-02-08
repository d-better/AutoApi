package com.better.apitest.mapper;

import com.better.apitest.domain.Environment;

import java.util.List;

public interface EnvironmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Environment record);

    int insertSelective(Environment record);

    Environment selectByPrimaryKey(Long id);

    List<Environment> selectByProjectId(Long projectId);

    int updateByPrimaryKeySelective(Environment record);

    int updateByPrimaryKeyWithBLOBs(Environment record);

    int updateByPrimaryKey(Environment record);
}