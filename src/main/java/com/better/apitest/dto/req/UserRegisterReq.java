package com.better.apitest.dto.req;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/29 2:22 下午
 * @Description:
 */
public class UserRegisterReq {
    private String userName;
    private String password;
    private String nameCn;
    private String account;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
