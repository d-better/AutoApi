package com.better.apitest.dto;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/28 8:49 下午
 * @Description:
 */
public class FormDataDto {
    /** 键 */
    private String key;

    /**  值 */
    private String value;

    public FormDataDto(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public FormDataDto() {

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
