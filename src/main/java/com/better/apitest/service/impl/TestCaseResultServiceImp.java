package com.better.apitest.service.impl;

import com.better.apitest.domain.TestCaseResult;
import com.better.apitest.mapper.TestCaseResultMapper;
import com.better.apitest.service.TestCaseResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/22 8:27 下午
 * @Description:
 */
@Service
public class TestCaseResultServiceImp implements TestCaseResultService {

    @Autowired
    private TestCaseResultMapper testCaseResultMapper;

    @Override
    public HashSet<TestCaseResult> getTestCaseListByTestCaseId(List<Long> testCaseId) {
        return testCaseResultMapper.selectByTestCaseId(testCaseId);
    }
}
