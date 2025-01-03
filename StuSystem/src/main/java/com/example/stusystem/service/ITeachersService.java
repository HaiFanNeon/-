package com.example.stusystem.service;

import com.example.stusystem.model.pojo.Teachers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2025-01-03
 */
public interface ITeachersService extends IService<Teachers> {

    void teacherLogin(Teachers teachers);
}
