package com.better.apitest.dto;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 10:56 下午
 * @Description:
 */
public class HeaderDto {
    /* 头部key */
    private String key;

    /* 头部值 */
    private String value;

    public HeaderDto() {

    }

    public HeaderDto(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
