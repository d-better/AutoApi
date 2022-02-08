package com.better.apitest.service;

import com.better.apitest.domain.DbConfigs;

import java.util.List;

public interface DbService {
    List<DbConfigs> getDbList(Long projectId);
    void saveDb(DbConfigs dbConfigs);
    void updateDb(DbConfigs dbConfigs);
    Boolean removeDb(DbConfigs dbConfigs);
}
