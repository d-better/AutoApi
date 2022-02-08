package com.better.apitest.service.impl;

import com.better.apitest.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: jingtian
 * @DateTime: 2022/2/8 4:07 下午
 * @Description:
 */

@Service
public class TestServiceImp implements TestService {

    private static final Logger LOG = LoggerFactory.getLogger(TestServiceImp.class);

    @Override
    public void helloService() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 1;

        if (a == b) {
            LOG.info("a==b");
        } else if (a == c) {
            LOG.info("a == c");
        } else if (a == d) {
            LOG.info("a == d");
        } else if (c == b) {
            LOG.info("c == b");
        } else if (d == b) {
            LOG.info("d == b");
        }
    }
}
