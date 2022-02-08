package com.better.apitest.service.impl;

import com.better.apitest.domain.Environment;
import com.better.apitest.mapper.EnvironmentMapper;
import com.better.apitest.service.EnvironmentService;
import com.better.apitest.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 10:10 下午
 * @Description:
 */
@Service
public class EnvironmentServiceImp implements EnvironmentService {

    @Autowired
    private EnvironmentMapper environmentMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public List<Environment> getEnvironmentList(Long projectId) {
        return environmentMapper.selectByProjectId(projectId);
    }

    @Override
    public void updateEnvironment(Environment environment) {
        environmentMapper.updateByPrimaryKeySelective(environment);
    }

    @Override
    public void saveEnvironment(Environment environment) {
        environment.setId(snowFlake.nextId());
        environmentMapper.insertSelective(environment);
    }

    @Override
    public Boolean removeEnvironment(Environment environment) {
        return environmentMapper.updateByPrimaryKeySelective(environment) > 0;
    }

    @Override
    public Environment getEnvironmentById(Long environmentId) {
        return environmentMapper.selectByPrimaryKey(environmentId);
    }

}
