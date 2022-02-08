package com.better.apitest.mapper;

import com.better.apitest.domain.Request;

import java.util.List;

public interface RequestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Request record);

    int insertSelective(Request record);

    int foreachInsertSelective(List<Request> requestList);

    Request selectByPrimaryKey(Long id);

    List<Request> selectByCaseId(Long caseId);

    int updateByPrimaryKeySelective(Request record);

    int updateByPrimaryKeyWithBLOBs(Request record);

    int updateByPrimaryKey(Request record);
}