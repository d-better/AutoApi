package com.better.apitest.dto.req;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/22 8:43 下午
 * @Description:
 */

import java.io.Serializable;

public class BaseQuery implements Serializable {
    protected Integer pageNo = 1;

    /**
     * 每页个数
     */
    protected Integer pageSize = 10;
    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 分页数据偏移量
     */
    public Integer getOffset() {
        computeOffset();
        return offset;
    }

    /**
     * 计算分页偏移量
     */
    public Integer computeOffset() {
        if (null == pageNo || null == pageSize) {
            return null;
        }
        offset = (pageNo - 1) * pageSize;
        return offset;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
