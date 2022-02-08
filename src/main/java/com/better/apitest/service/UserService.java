package com.better.apitest.service;

import com.better.apitest.domain.User;
import com.better.apitest.dto.req.UserLoginReq;
import com.better.apitest.dto.req.UserRegisterReq;
import com.better.apitest.dto.resp.CommonResp;

public interface UserService {
    CommonResp login(UserLoginReq userLoginReq);
    Boolean register(UserRegisterReq userRegisterReq);
    User getUserInfo();
}
