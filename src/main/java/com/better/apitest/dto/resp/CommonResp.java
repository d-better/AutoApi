package com.better.apitest.dto.resp;

import com.alibaba.fastjson.JSON;
import com.better.apitest.enums.ErrorEnum;
import com.better.apitest.exception.BusinessException;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/20 8:05 下午
 * @Description:
 */
public class CommonResp<T> {
    /**
     * 状态
     */
    private boolean status;

    private Long count = 0L;
    /**
     * 错误消息
     */
    private String message = "";

    /**
     * 结果对象
     */
    private T entry;


    public static CommonResp success() {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(true);
        return commonResp;
    }

    public static CommonResp success(String message) {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(true);
        commonResp.setMessage(message);
        return commonResp;
    }

    public static CommonResp success(Object entry) {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(true);
        commonResp.setEntry(entry);
        return commonResp;
    }

    public static CommonResp success(Long count, Object entry) {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(true);
        commonResp.setCount(count);
        commonResp.setEntry(entry);
        return commonResp;
    }

    public static CommonResp success(List entryList, Long count) {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(true);
        commonResp.setCount(count);
        commonResp.setEntry(entryList);
        return commonResp;
    }

    public static CommonResp fail(ErrorEnum errorEnum) {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(false);
        commonResp.setMessage(errorEnum.getName());
        return commonResp;
    }

    public static CommonResp fail(String msg) {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(false);
        commonResp.setMessage(msg);
        return commonResp;
    }

    public static CommonResp fail(BusinessException e) {
        CommonResp commonResp= new CommonResp();
        commonResp.setStatus(false);
        commonResp.setMessage(e.getErrorMessage());
        return commonResp;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    public String toString() {
        String str;
        try {
            str = JSON.toJSONString(this);
        }catch (RuntimeException e){
            str = "";
        }
        return str;
    }
}
