package com.better.apitest.service.impl;

import com.better.apitest.domain.Request;
import com.better.apitest.dto.RequestDto;
import com.better.apitest.dto.req.SaveTestCaseAndRequestReq;
import com.better.apitest.mapper.RequestMapper;
import com.better.apitest.service.RequestService;
import com.better.apitest.utils.OrikaMapperUtils;
import com.better.apitest.utils.SnowFlake;
import com.better.apitest.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/25 12:39 上午
 * @Description:
 */

@Service
public class RequestServiceImp implements RequestService {

    @Autowired
    RequestMapper requestMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void saveRequestList(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq) {
        List<Request> requests = OrikaMapperUtils.mapList(saveTestCaseAndRequestReq.getRequestList(), RequestDto.class, Request.class);
        List<Request> requestList = Tools.requestDtoToRequest(saveTestCaseAndRequestReq.getRequestList(),requests);
        requestList.forEach(x -> {
            x.setId(snowFlake.nextId());
            x.setTestCaseId(saveTestCaseAndRequestReq.getId());
        });
        requestMapper.foreachInsertSelective(requestList);
    }

    public void updateRequestList(SaveTestCaseAndRequestReq saveTestCaseAndRequestReq) {
        List<Request> requests = OrikaMapperUtils.mapList(saveTestCaseAndRequestReq.getRequestList(), RequestDto.class, Request.class);
        requests.forEach(x -> {
            if (ObjectUtils.isEmpty(x.getId())){
                x.setTestCaseId(saveTestCaseAndRequestReq.getId());
                requestMapper.insertSelective(x);
            } else {
                requestMapper.updateByPrimaryKeySelective(x);
            }
        });
    }

    @Override
    public List<Request> queryRequestListByCaseId(Long caseId) {
        return requestMapper.selectByCaseId(caseId);
    }
}
