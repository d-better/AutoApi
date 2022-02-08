package com.better.apitest.dto;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/25 6:08 下午
 * @Description:
 */
public class ApiVariableDto {
    /**
     * 变量名称
     */
    private String name;
    /**
     * 数据源
     */
    private String source;
    /**
     * 表达式
     */
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
