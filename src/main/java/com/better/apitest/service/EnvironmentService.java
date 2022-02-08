package com.better.apitest.service;

import com.better.apitest.domain.Environment;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 10:08 下午
 * @Description:
 */
public interface EnvironmentService {
    List<Environment> getEnvironmentList(Long projectId);
    void updateEnvironment(Environment environment);
    void saveEnvironment(Environment environment);
    Boolean removeEnvironment(Environment environment);
    Environment getEnvironmentById(Long environmentId);
}
