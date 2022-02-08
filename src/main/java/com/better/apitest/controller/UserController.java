package com.better.apitest.controller;

import com.better.apitest.dto.req.UserLoginReq;
import com.better.apitest.dto.req.UserRegisterReq;
import com.better.apitest.dto.resp.CommonResp;
import com.better.apitest.enums.ErrorEnum;
import com.better.apitest.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private com.better.apitest.service.UserService userService;


    @PostMapping("/login")
    public CommonResp login(@RequestBody UserLoginReq userLoginReq) {
        userLoginReq.setPassword(MD5.encrytMD5(userLoginReq.getPassword()));
        return userService.login(userLoginReq);
    }

    @PostMapping("/register")
    public CommonResp register(@RequestBody UserRegisterReq userRegisterReq){
        userRegisterReq.setPassword(MD5.encrytMD5(userRegisterReq.getPassword()));
        if (userService.register(userRegisterReq)){
            return CommonResp.success();
        }else {
            return CommonResp.fail(ErrorEnum.DEFAULT_ERROR);
        }
    }

    @GetMapping("/info")
    public CommonResp getUserInfo() {
        return CommonResp.success(userService.getUserInfo());
    }
}
