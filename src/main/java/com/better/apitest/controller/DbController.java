package com.better.apitest.controller;

import com.better.apitest.domain.DbConfigs;
import com.better.apitest.domain.User;
import com.better.apitest.dto.req.DbConfigReq;
import com.better.apitest.dto.resp.CommonResp;
import com.better.apitest.dto.resp.DbConfigResp;
import com.better.apitest.enums.ErrorEnum;
import com.better.apitest.service.DbService;
import com.better.apitest.utils.OrikaMapperUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: jingtian
 * @DateTime: 2021/7/22 4:55 下午
 * @Description:
 */
@RestController
@RequestMapping("/db")
public class DbController extends BaseController{


    @Autowired
    private DbService dbService;
    /**
     * 数据库配置列表
     * @return
     */
    @GetMapping("/list")
    public CommonResp<List<DbConfigs>> dbList(@RequestParam Long projectId) {
        User user = getUserInfo();
        if ("admin".equals(user.getAccount()) | "QA".equals(user.getAccount())) {
            List<DbConfigs> dbConfigList = dbService.getDbList(projectId);
            List<DbConfigResp> dbConfigRespList = OrikaMapperUtils.mapList(dbConfigList, DbConfigs.class, DbConfigResp.class);
            PageInfo<DbConfigResp> pageInfo = new PageInfo<>(dbConfigRespList);
            return CommonResp.success(dbConfigRespList, pageInfo.getTotal());
        } else {
            return CommonResp.fail(ErrorEnum.HAS_NO_POWER);
        }
    }

    /**
     * 保存数据库配置
     * @param dbConfigReqList
     * @return
     */
    @PostMapping("/save")
    public CommonResp saveEnvironment(@RequestBody List<DbConfigReq> dbConfigReqList) {
        List<DbConfigs> dbConfigsList = OrikaMapperUtils.mapList(dbConfigReqList, DbConfigReq.class, DbConfigs.class);
        User user = getUserInfo();
        dbConfigsList.forEach(x->{
            if (!ObjectUtils.isEmpty(x.getId())) {
//                修改
                x.setUpdateBy(user.getId());
                dbService.updateDb(x);
            } else {
//            新增
                x.setCreateBy(user.getId());
                dbService.saveDb(x);
            }
        });
        return CommonResp.success();
    }

    @PostMapping ("/remove")
    public CommonResp removeEnvironment(@RequestBody Long id){
        DbConfigs dbConfigs = new DbConfigs();
        dbConfigs.setId(id);
        dbConfigs.setDelFlag(true);
        if (dbService.removeDb(dbConfigs)){
            return CommonResp.success();
        } else {
            return CommonResp.fail(ErrorEnum.DATA_NOT_EXIST);
        }
    }
}
