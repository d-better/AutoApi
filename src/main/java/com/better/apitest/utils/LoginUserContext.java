package com.better.apitest.utils;


import com.better.apitest.domain.User;

import java.io.Serializable;

public class LoginUserContext implements Serializable {

    private static ThreadLocal<User> user = new ThreadLocal<>();

    public static User getUser() {
        return user.get();
    }

    public static User setUser(User user) {
        LoginUserContext.user.set(user);
        return user;
    }

}
