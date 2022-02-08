package com.better.apitest.mapper;

import com.better.apitest.domain.TestCaseResult;

import java.util.HashSet;
import java.util.List;

public interface TestCaseResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestCaseResult record);

    int insertSelective(TestCaseResult record);

    TestCaseResult selectByPrimaryKey(Long id);

    HashSet<TestCaseResult> selectByTestCaseId(List<Long> testCaseId);

    int updateByPrimaryKeySelective(TestCaseResult record);

    int updateByPrimaryKey(TestCaseResult record);
}