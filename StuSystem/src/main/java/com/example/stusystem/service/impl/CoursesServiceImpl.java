package com.example.stusystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.example.stusystem.model.pojo.Courses;
import com.example.stusystem.mapper.CoursesMapper;
import com.example.stusystem.service.ICoursesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2025-01-03
 */
@Service
@Slf4j
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, Courses> implements ICoursesService {

    @Override
    public List<Courses> getCoursesByTeacherName(String name) {

        QueryWrapper<Courses> queryWrapper = new QueryWrapper<Courses>()
                .select("*")
                .like("teacher_name", name);

        List<Courses> list = list(queryWrapper);
        log.info("list {}",list.toString());
        return list;
    }
}
