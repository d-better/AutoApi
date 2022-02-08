package com.better.apitest.controller;

import com.alibaba.fastjson.JSON;
import com.better.apitest.domain.User;
import com.better.apitest.utils.LoginUserContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/22 10:58 上午
 * @Description:
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(BaseController.class);

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Autowired
    RedisTemplate redisTemplate;

    public User getUserInfo(){
        String token = request.getHeader("Authorization");
        Object object = redisTemplate.opsForValue().get(token);
        return LoginUserContext.setUser(JSON.parseObject((String) object, User.class));
    }
}
