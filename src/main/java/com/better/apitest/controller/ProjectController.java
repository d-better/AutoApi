package com.better.apitest.controller;

import com.better.apitest.domain.Project;
import com.better.apitest.domain.User;
import com.better.apitest.dto.req.ProjectSaveReq;
import com.better.apitest.dto.req.RemoveProjectReq;
import com.better.apitest.dto.resp.CommonResp;
import com.better.apitest.dto.resp.ProjectResp;
import com.better.apitest.enums.ErrorEnum;
import com.better.apitest.exception.BusinessException;
import com.better.apitest.service.ProjectService;
import com.better.apitest.utils.OrikaMapperUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectService projectService;

    /**
     * 获取项目列表
     * @return
     */
    @GetMapping ("/list")
    public CommonResp<List<ProjectResp>> projectList(){
        List<Project> projectList = projectService.getProjectList();
        List<ProjectResp> projectRespList = OrikaMapperUtils.mapList(projectList, Project.class, ProjectResp.class);
        PageInfo<ProjectResp> pageInfo = new PageInfo<>(projectRespList);
        return CommonResp.success(projectRespList,pageInfo.getTotal());
    }

    /**
     * 保存项目接口
     * @param projectSaveReq
     * @param
     * @return
     */
    @PostMapping("/save")
    public CommonResp projectSave(@RequestBody ProjectSaveReq projectSaveReq){
        Project project = OrikaMapperUtils.map(projectSaveReq, Project.class);
        User user = getUserInfo();
        if (!ObjectUtils.isEmpty(user)){
            if (ObjectUtils.isEmpty(project.getId())){
//            新增项目
                project.setCreateBy(user.getId());
                projectService.saveProject(project);
            } else {
//            修改项目
                project.setUpdateBy(user.getId());
                projectService.updateProject(project);
            }
        } else {
            LOG.error("用户信息获取失败");
            throw new BusinessException(ErrorEnum.DEFAULT_ERROR);
        }
        return CommonResp.success();
    }

    @PostMapping ("/remove")
    public CommonResp removeProject(@RequestBody RemoveProjectReq removeProjectReq){
        Project project = OrikaMapperUtils.map(removeProjectReq, Project.class);
        if (projectService.removeProject(project)){
            return CommonResp.success();
        } else {
            return CommonResp.fail(ErrorEnum.DATA_NOT_EXIST);
        }

    }

}
