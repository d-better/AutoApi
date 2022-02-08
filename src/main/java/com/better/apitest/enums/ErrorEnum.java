package com.better.apitest.enums;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/20 7:47 下午
 * @Description: 错误信息枚举
 */
public enum ErrorEnum {

    /**
     * 常见错误
     */
    DEFAULT_ERROR(100, "默认错误"),
    PARAMS_ERROR(101, "参数错误"),
    HAS_NO_POWER(102, "没有权限操作"),
    DB_ERROR(103, "数据库操作错误"),
    DATA_NOT_EXIST(104, "数据不存在"),
    NET_ERROR(105, "网络异常，请稍后重试"),
    NET_TIMEOUT_ERROR(106, "网络超时"),


    /**
     * 用户相关
     */
    USER_NOT_LOGIN(10212, "用户未登录"),
    USER_NOT_EXIT(500, "用户名或密码错误"),
    USER_NO_EXISTENT(501, "用户不存在"),
    WXHC_DISTRIBUTOR_NAME_OUT_LENGTH(1137, "请输入1～16位的昵称"),
    WXHC_DISTRIBUTOR_NOTE_OUT_LENGTH(1138, "描述长度请控制在1000位以内"),
    USER_LOGIN_INFO_ERROR(113, "用户登录信息异常"),
    ;

    /**
     * 值
     */
    private int value;
    /**
     * 名称
     */
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ErrorEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
