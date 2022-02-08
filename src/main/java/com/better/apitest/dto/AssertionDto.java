package com.better.apitest.dto;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/25 12:17 下午
 * @Description:
 */
public class AssertionDto {
    /**
     * 断言名称
     */
    private String name;
    /**
     * 数据源
     */
    private String source;
    /**
     * 比较规则
     */
    private String comparison;
    /**
     * 表达式
     */
    private String content;
    /**
     * 预期值
     */
    private String expectValue;
    /**
     * 是否断言成功
     */
    private boolean success;
    /**
     * 断言失败信息
     */
    private String failedReason;

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

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExpectValue() {
        return expectValue;
    }

    public void setExpectValue(String expectValue) {
        this.expectValue = expectValue;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }
}
