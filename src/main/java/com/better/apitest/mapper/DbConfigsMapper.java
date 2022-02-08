package com.better.apitest.mapper;

import com.better.apitest.domain.DbConfigs;

import java.util.List;

public interface DbConfigsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DbConfigs record);

    int insertSelective(DbConfigs record);

    List<DbConfigs> selectDbList(Long projectId);

    DbConfigs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DbConfigs record);

    int updateByPrimaryKey(DbConfigs record);
}