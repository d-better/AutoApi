package com.better.apitest.service;

import com.better.apitest.domain.TestCaseResult;

import java.util.HashSet;
import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 10:08 下午
 * @Description:
 */
public interface TestCaseResultService {
    HashSet<TestCaseResult> getTestCaseListByTestCaseId(List<Long> testCaseId);
}
