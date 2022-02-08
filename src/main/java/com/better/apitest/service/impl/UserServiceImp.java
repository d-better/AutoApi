package com.better.apitest.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.better.apitest.domain.User;
import com.better.apitest.dto.req.UserLoginReq;
import com.better.apitest.dto.req.UserRegisterReq;
import com.better.apitest.dto.resp.CommonResp;
import com.better.apitest.enums.ErrorEnum;
import com.better.apitest.mapper.UserMapper;
import com.better.apitest.service.UserService;
import com.better.apitest.utils.OrikaMapperUtils;
import com.better.apitest.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

;

@Service
public class UserServiceImp implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImp.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private RedisTemplate redisTemplate;

    public User getUserByName(UserLoginReq userLoginReq) {
        User userDb = userMapper.selectByUserName(userLoginReq.userName);
        return userDb;
    }

    @Override
    public CommonResp login(UserLoginReq userLoginReq) {
        User userDb = getUserByName(userLoginReq);
        if (ObjectUtils.isEmpty(userDb)) {
            LOG.info("用户名不存在, {}", userLoginReq.getUserName());
            return CommonResp.fail(ErrorEnum.USER_NO_EXISTENT);
        } else {
            if (userDb.getPassword().equals(userLoginReq.getPassword())) {
//                登陆成功
                LOG.info("用户{}，登陆成功",userDb.getUserName());
                Long token = snowFlake.nextId();
                LOG.info("生成单点登录token：{}，并放入redis中", token);
                userDb.setToken(token.toString());
                redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userDb),3600*24, TimeUnit.SECONDS);
                return CommonResp.success(userDb);
            } else {
//                密码匹配不通过
                LOG.info("密码匹配不通过，输入密码{},数据库密码:{}",userLoginReq.getPassword(),userDb.getPassword());
                return CommonResp.fail(ErrorEnum.USER_NOT_EXIT);
            }
        }
    }

    @Override
    public Boolean register(UserRegisterReq userRegisterReq) {
        User user = OrikaMapperUtils.map(userRegisterReq, User.class);
        user.setId(snowFlake.nextId());
        return userMapper.insertSelective(user) > 0;
    }

    @Override
    public User getUserInfo() {
        User userInfo = getUserInfo();
        return userInfo;
    }
}
