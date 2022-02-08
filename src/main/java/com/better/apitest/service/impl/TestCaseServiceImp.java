package com.better.apitest.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.better.apitest.domain.Environment;
import com.better.apitest.domain.Request;
import com.better.apitest.domain.TestCase;
import com.better.apitest.domain.TestCaseResult;
import com.better.apitest.dto.*;
import com.better.apitest.dto.req.GetTestCaseListReq;
import com.better.apitest.dto.req.SaveTestCaseAndRequestReq;
import com.better.apitest.dto.resp.TestCaseDetailResp;
import com.better.apitest.dto.resp.TestCaseListResp;
import com.better.apitest.mapper.TestCaseMapper;
import com.better.apitest.service.EnvironmentService;
import com.better.apitest.service.RequestService;
import com.better.apitest.service.TestCaseResultService;
import com.better.apitest.service.TestCaseService;
import com.better.apitest.utils.OrikaMapperUtils;
import com.better.apitest.utils.SnowFlake;
import com.better.apitest.utils.Tools;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 10:10 下午
 * @Description:
 */
@Service
public class TestCaseServiceImp implements TestCaseService {

    private static final Logger LOG = LoggerFactory.getLogger(TestCaseServiceImp.class);

    @Autowired
    private TestCaseMapper testCaseMapper;

    @Autowired
    private TestCaseResultService testCaseResultService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private EnvironmentService environmentService;

    @Override
    public List<TestCaseListResp> getTestCaseList(GetTestCaseListReq getTestCaseListReq) {
//        查询指定页数和条数的测试用例
        PageHelper.startPage(getTestCaseListReq.getPageNo(), getTestCaseListReq.getPageSize());
        List<TestCase> testCaseList = testCaseMapper.selectByProjectId(getTestCaseListReq.getProjectId());
        List<Long> testCaseIdList = new ArrayList<>();
//        循环取出testCaseId，用来取success和lastRunDate两个字端
        testCaseList.forEach(x -> {
            testCaseIdList.add(x.getId());
        });
        LOG.info("查询测试用例的运行结果testCaseId:{}", testCaseIdList);
//        取TestCaseResult
        HashSet<TestCaseResult> testCaseResultList = testCaseResultService.getTestCaseListByTestCaseId(testCaseIdList);
//        对象转换
        List<TestCaseListResp> testCaseListRespList = OrikaMapperUtils.mapList(testCaseList, TestCase.class, TestCaseListResp.class);
        if (!ObjectUtils.isEmpty(testCaseResultList)) {
            for (int i = 0; i < testCaseListRespList.size(); i++) {
                testCaseListRespList.get(i).setSuccess(testCaseResultList.iterator().next().getSuccess());
                testCaseListRespList.get(i).setLastRunDate(testCaseResultList.iterator().next().getCreateDate());
            }
        }
        return testCaseListRespList;
    }

    @Override
    public TestCaseDetailResp getTestCaseById(Long testCaseId) {
        TestCase testCase = testCaseMapper.selectByPrimaryKey(testCaseId);
        TestCaseDetailResp testCaseDetailResp = OrikaMapperUtils.map(testCase, TestCaseDetailResp.class);
        List<Request> requestList = requestService.queryRequestListByCaseId(testCaseId);
        List<RequestDto> requestDtoList = OrikaMapperUtils.mapList(requestList, Request.class, RequestDto.class);
        List<RequestDto> requestDtos = Tools.requestToRequestDto(requestList, requestDtoList);
        testCaseDetailResp.setRequestDtoList(requestDtos);
        return testCaseDetailResp;
    }

    @Override
    public void runTestCaseById(Long testCaseId, Long userId) {
        TestCase testCase = testCaseMapper.selectByPrimaryKey(testCaseId);
        List<Request> requestList = requestService.queryRequestListByCaseId(testCaseId);
        Environment environment = environmentService.getEnvironmentById(testCase.getEnvironmentId());
        EnvironmentDto environmentDto = null;
        if (!ObjectUtils.isEmpty(environment)) {
            environmentDto = OrikaMapperUtils.map(environment, EnvironmentDto.class);
            environmentDto.setApiUrlParameterList(JSONObject.parseArray(environment.getApiUrlParameter(), UrlParameterDto.class));
            environmentDto.setApiHeaderList(JSONObject.parseArray(environment.getApiHeaders(), HeaderDto.class));
            environmentDto.setInitVariableList(JSONObject.parseArray(environment.getInitVariables(), VariableDto.class));
            environmentDto.setVariablesByFuncList(JSONObject.parseArray(environment.getVariablesByFunc(), FuncVariableDto.class));
        }
//        environmentDto.getInitVariableList()
        RunTestCaseDto runTestCaseDto = OrikaMapperUtils.map(testCase, RunTestCaseDto.class);
        List<RequestDto> requestDtoList = OrikaMapperUtils.mapList(requestList, Request.class, RequestDto.class);
        List<RequestDto> requestDtos = Tools.requestToRequestDto(requestList, requestDtoList);
        List<RunRequestDto> runRequestDtoList = new ArrayList<>();
        EnvironmentDto finalEnvironmentDto = environmentDto;
        requestDtos.forEach(x->{
            runRequestDtoList.add(new RunRequestDto(x, finalEnvironmentDto));
        });

        runTestCaseDto.setRequestDtoList(runRequestDtoList);
        runTestCaseDto.getRequestDtoList().forEach(x -> {
            if (Math.abs(x.getSleepTime()) != 0) {
                try {
                    LOG.info("-----延迟执行" + x.getSleepTime() + "S");
                    Thread.sleep(Math.abs(x.getSleepTime()) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void updateTestCase(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq) {
        TestCase testCase = OrikaMapperUtils.map(saveTestCaseAndRequestReq, TestCase.class);
        testCaseMapper.updateByPrimaryKeySelective(testCase);
        requestService.updateRequestList(saveTestCaseAndRequestReq);
    }

    @Override
    @Transactional
    //这里会操作两张外健关系表，需要事物管理
    public void saveTestCase(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq) {
        saveTestCaseAndRequestReq.setId(snowFlake.nextId());
        TestCase testCase = OrikaMapperUtils.map(saveTestCaseAndRequestReq, TestCase.class);
        testCaseMapper.insertSelective(testCase);
        requestService.saveRequestList(saveTestCaseAndRequestReq);
    }

    @Override
    public Boolean removeTestCase(TestCase testCase) {
        return testCaseMapper.updateByPrimaryKeySelective(testCase) > 0;
    }
}
