package com.better.apitest.service.impl;

import com.better.apitest.domain.DbConfigs;
import com.better.apitest.mapper.DbConfigsMapper;
import com.better.apitest.service.DbService;
import com.better.apitest.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceImp implements DbService {
    private static final Logger LOG = LoggerFactory.getLogger(DbServiceImp.class);
    @Autowired
    private DbConfigsMapper dbConfigsMapper;

    @Autowired
    private SnowFlake snowFlake;

    @Override
    public List<DbConfigs> getDbList(Long projectId) {
        return dbConfigsMapper.selectDbList(projectId);
    }

    @Override
    public void saveDb(DbConfigs dbConfigs) {
        dbConfigs.setId(snowFlake.nextId());
        dbConfigsMapper.insertSelective(dbConfigs);
    }

    @Override
    public Boolean removeDb(DbConfigs dbConfigs) {
        dbConfigs.setDelFlag(true);
        return dbConfigsMapper.updateByPrimaryKeySelective(dbConfigs) > 0;
    }

    @Override
    public void updateDb(DbConfigs dbConfigs) {
        dbConfigsMapper.updateByPrimaryKeySelective(dbConfigs);
    }

}
