package com.better.apitest.service;

import com.better.apitest.domain.Request;

/**
 * @Author: jingtian
 * @DateTime: 2021/8/6 7:43 下午
 * @Description:
 */
public interface ApiRequestService {
    void runRequest(Request request, String testCaseResultId);
}
