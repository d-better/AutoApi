package com.better.apitest.mapper;

import com.better.apitest.domain.TestCase;

import java.util.List;

public interface TestCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestCase record);

    int insertSelective(TestCase record);

    TestCase selectByPrimaryKey(Long id);

    List<TestCase> selectByProjectId(Long projectId);

    int updateByPrimaryKeySelective(TestCase record);

    int updateByPrimaryKey(TestCase record);
}