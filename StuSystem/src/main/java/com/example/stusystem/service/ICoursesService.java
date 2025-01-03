package com.example.stusystem.service;

import com.example.stusystem.model.pojo.Courses;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2025-01-03
 */
public interface ICoursesService extends IService<Courses> {

    List<Courses> getCoursesByTeacherName(String name);
}
