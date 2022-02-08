package com.better.apitest.service;

import com.better.apitest.domain.TestCase;
import com.better.apitest.dto.req.GetTestCaseListReq;
import com.better.apitest.dto.req.SaveTestCaseAndRequestReq;
import com.better.apitest.dto.resp.TestCaseDetailResp;
import com.better.apitest.dto.resp.TestCaseListResp;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 10:08 下午
 * @Description:
 */
public interface TestCaseService {
    List<TestCaseListResp> getTestCaseList(GetTestCaseListReq getTestCaseListReq);
    void updateTestCase(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq);
    void saveTestCase(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq);
    Boolean removeTestCase(TestCase testCase);
    TestCaseDetailResp getTestCaseById(Long testCaseId);
    void runTestCaseById(Long testCaseId, Long userId);
}
