package com.example.stusystem.controller.teacher;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.stusystem.common.BaseContext;
import com.example.stusystem.model.pojo.Teachers;
import com.example.stusystem.service.ITeachersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2025-01-03
 */

@Api(tags = "教师管理")
@RestController
@Slf4j
@RequestMapping("/teacher")
public class TeachersController {

    @Autowired
    public ITeachersService iTeachersService;

    @ApiOperation("教师登录")
    @RequestMapping("/login")
    public String teacherLogin(Teachers teachers) throws Exception {
        log.info(teachers.toString());
        if (teachers == null) {
            // 抛出异常
            throw new Exception("用户名或密码为空");
        }
        LambdaQueryWrapper<Teachers> wrapper = new QueryWrapper<Teachers>().lambda()
                .eq(teachers.getTeacherNo() != null, Teachers::getTeacherNo, teachers.getTeacherNo())
                .eq(teachers.getName() != null, Teachers::getName , teachers.getName());
        Teachers retTeacher = iTeachersService.getOne(wrapper);
        if (retTeacher == null) {
            // 抛出异常
            throw new Exception("用户名或密码错误");
        }
        log.info(retTeacher.toString());
        BaseContext.setThreadLocal(Long.valueOf(retTeacher.getTeacherId()));
        return "success";
    }

    @ApiOperation("教师登出")
    @RequestMapping("/logout")
    public String teacherLogout() {
        BaseContext.setThreadLocal(0L);
        return "success";
    }

    @ApiOperation("教师注册")
    @RequestMapping("/register")
    public String teacherRegister(Teachers teachers) throws Exception {
        boolean save = iTeachersService.save(teachers);
        if (save) {
            return "success";
        }
        throw new Exception("注册失败");
    }

    @ApiOperation("全体教师信息查询")
    @RequestMapping("/teacherList")
    public List<Teachers> teacherList() {
        List<Teachers> list = iTeachersService.list();
        return list;
    }

    @ApiOperation("修改个人信息")
    @RequestMapping("/updateOne")
    public String updateOne(Teachers teachers) throws Exception {
        boolean update = iTeachersService.updateById(teachers);
        if (update) {
            return "success";
        }
        throw new Exception("修改失败");
    }

    @ApiOperation("删除教师信息")
    @RequestMapping("/deleteOne")
    public String deleteOne() throws Exception {
        boolean remove = iTeachersService.removeById(BaseContext.getCurrentId());
        if (remove) {
            return "success";
        }
        throw new Exception("删除失败");
    }
}
