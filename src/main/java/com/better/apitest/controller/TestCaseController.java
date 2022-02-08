package com.better.apitest.controller;

import com.better.apitest.domain.User;
import com.better.apitest.dto.req.GetTestCaseListReq;
import com.better.apitest.dto.req.SaveTestCaseAndRequestReq;
import com.better.apitest.dto.resp.CommonResp;
import com.better.apitest.dto.resp.TestCaseListResp;
import com.better.apitest.enums.ErrorEnum;
import com.better.apitest.service.TestCaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/22 6:11 下午
 * @Description:
 */

@RestController
@RequestMapping("/case")
public class TestCaseController extends BaseController {

    @Autowired
    private TestCaseService testCaseService;
//

    @PostMapping("/getCaseList")
    public CommonResp getTestCaseList(@RequestBody GetTestCaseListReq getTestCaseListReq) {
        User userInfo = getUserInfo();
        if ("admin".equals(userInfo.getAccount()) | "QA".equals(userInfo.getAccount())) {
            PageHelper.startPage(getTestCaseListReq.getPageNo(), getTestCaseListReq.getPageSize());
            List<TestCaseListResp> testCaseList = testCaseService.getTestCaseList(getTestCaseListReq);
            PageInfo<TestCaseListResp> pageInfo = new PageInfo<>(testCaseList);
            return CommonResp.success(pageInfo.getTotal(), testCaseList);
        } else {
            return CommonResp.fail(ErrorEnum.HAS_NO_POWER);
        }
    }

    @GetMapping("getCaseDetail")
    public CommonResp getTestCaseDetail(@RequestParam Long testCaseId) {
        return CommonResp.success(testCaseService.getTestCaseById(testCaseId));
    }

    @PostMapping("/saveCase")
    public CommonResp saveTestCaseAndRequest(@RequestBody SaveTestCaseAndRequestReq saveTestCaseAndRequestReq) {
        User userInfo = getUserInfo();
        if (ObjectUtils.isEmpty(saveTestCaseAndRequestReq.getId())) {
            saveTestCaseAndRequestReq.setCreateBy(userInfo.getId());
//            ID为空走新增逻辑
            testCaseService.saveTestCase(saveTestCaseAndRequestReq);
        } else {
//            走修改逻辑
            saveTestCaseAndRequestReq.setUpdateBy(userInfo.getId());
            testCaseService.updateTestCase(saveTestCaseAndRequestReq);
        }
        return CommonResp.success("保存成功");
    }

    @GetMapping ("/runCase")
    public CommonResp runTestCaseById(@RequestParam Long testCaseId) {
        User userInfo = getUserInfo();
        testCaseService.runTestCaseById(testCaseId, userInfo.getId());
        return CommonResp.success("运行成功");
    }

}