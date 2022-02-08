package com.better.apitest.controller;

import com.better.apitest.dto.resp.CommonResp;
import com.better.apitest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jingtian
 * @DateTime: 2022/2/8 4:04 下午
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

    @Autowired
    private TestService testService;
    @GetMapping("hello")
    public CommonResp getTestCaseDetail() {
        testService.helloService();
        return CommonResp.success();
    }
}
