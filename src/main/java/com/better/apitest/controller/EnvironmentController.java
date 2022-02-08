package com.better.apitest.controller;

import com.better.apitest.domain.Environment;
import com.better.apitest.domain.User;
import com.better.apitest.dto.req.SaveEnvironmentReq;
import com.better.apitest.dto.resp.CommonResp;
import com.better.apitest.dto.resp.EnvironmentResp;
import com.better.apitest.enums.ErrorEnum;
import com.better.apitest.service.EnvironmentService;
import com.better.apitest.utils.OrikaMapperUtils;
import com.better.apitest.utils.Tools;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: jingtian
 * @DateTime: 2021/7/21 9:54 下午
 * @Description:
 */

@RestController
@RequestMapping("/environment")
public class EnvironmentController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(EnvironmentController.class);

    @Autowired
    EnvironmentService environmentService;

    /**
     * 获取环境变量列表
     */
    @GetMapping("/list")
    public CommonResp environmentList(@RequestParam Long projectId) {
        User user = this.getUserInfo();
        if ("admin".equals(user.getAccount()) | "QA".equals(user.getAccount())) {
            List<Environment> environmentList = environmentService.getEnvironmentList(projectId);
            List<EnvironmentResp> environmentRespList = OrikaMapperUtils.mapList(environmentList, Environment.class, EnvironmentResp.class);
            List<EnvironmentResp> environmentResps = Tools.environmentListToEnvironmentRespList(environmentList, environmentRespList);
            PageInfo<EnvironmentResp> pageInfo = new PageInfo<>(environmentResps);
            return CommonResp.success(environmentResps, pageInfo.getTotal());
        } else {
            return CommonResp.fail(ErrorEnum.HAS_NO_POWER);
        }

    }

    /**
     * 保存环境变量接口
     *
     * @param saveEnvironmentReqList
     * @return
     */
    @PostMapping("/save")
    public CommonResp saveEnvironment(@RequestBody List<SaveEnvironmentReq> saveEnvironmentReqList) {
        List<Environment> environmentList = OrikaMapperUtils.mapList(saveEnvironmentReqList, SaveEnvironmentReq.class, Environment.class);
        List<Environment> environments = Tools.saveEnvironmentReqListToEnvironmentList(saveEnvironmentReqList, environmentList);
        User user = this.getUserInfo();
        environments.forEach(x->{
            if (!ObjectUtils.isEmpty(x.getId())) {
//                修改
                x.setUpdateBy(user.getId());
                environmentService.updateEnvironment(x);
        } else {
//            新增
                x.setCreateBy(user.getId());
                environmentService.saveEnvironment(x);
            }
        });
        return CommonResp.success();
    }

    @PostMapping ("/remove")
    public CommonResp removeEnvironment(@RequestBody Long id){
        Environment environment = new Environment();
        environment.setId(id);
        environment.setDelFlag(true);
        if (environmentService.removeEnvironment(environment)){
            return CommonResp.success();
        } else {
            return CommonResp.fail(ErrorEnum.DATA_NOT_EXIST);
        }
    }
}
