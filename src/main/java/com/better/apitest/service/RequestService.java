package com.better.apitest.service;

import com.better.apitest.domain.Request;
import com.better.apitest.dto.req.SaveTestCaseAndRequestReq;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/25 12:38 上午
 * @Description:
 */
public interface RequestService {
    void saveRequestList(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq);

    void updateRequestList(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq);

    List<Request> queryRequestListByCaseId(Long caseID);
}
