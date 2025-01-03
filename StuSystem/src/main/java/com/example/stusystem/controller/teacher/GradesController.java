package com.example.stusystem.controller.teacher;


import com.example.stusystem.common.BaseContext;
import com.example.stusystem.model.pojo.Courses;
import com.example.stusystem.model.pojo.Grades;
import com.example.stusystem.model.pojo.Students;
import com.example.stusystem.model.pojo.Teachers;
import com.example.stusystem.model.vo.GradeVO;
import com.example.stusystem.service.ICoursesService;
import com.example.stusystem.service.IGradesService;
import com.example.stusystem.service.IStudentsService;
import com.example.stusystem.service.ITeachersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2025-01-03
 */

@Slf4j
@Api(tags = "教师端成绩管理")
@RestController
@RequestMapping("/grades")
public class GradesController {
    @Autowired
    private IGradesService iGradesService;
    @Autowired
    private ICoursesService iCoursesService;
    @Autowired
    private ITeachersService iTeachersService;
    @Autowired
    private IStudentsService iStudentsService;
    // 获取当前老师的 ID（假设通过登录态获取）
    private Integer getCurrentTeacherId() {
        // 这里根据你的项目实际情况获取
        return 1; // 示例，假设当前老师 ID 为 1
    }
    @ApiOperation("当前老师查看当前课程的成绩")
    @RequestMapping("/getGradeByTeacher")
    public List<GradeVO> getGradeByTeacher() throws Exception {
        // 假设当前老师 ID 通过登录态获取
        Integer currentTeacherId = getCurrentTeacherId();
        // 获取所有成绩数据
        List<Grades> gradesList = iGradesService.list();
        Teachers byId = iTeachersService.getById(currentTeacherId);
        // 查询当前老师所教的课程
        List<Courses> teacherCourses = iCoursesService.getCoursesByTeacherName(byId.getName());
        log.info("teacherCourses {}",teacherCourses.toString());
        Set<Integer> courseIds = teacherCourses.stream()
                .map(Courses::getCourseId)
                .collect(Collectors.toSet());
        log.info("courseIds {}",courseIds.toString());
        // 过滤出属于当前老师课程的成绩
        List<Grades> filteredGrades = gradesList.stream()
                .filter(grade -> courseIds.contains(grade.getCourseId()))
                .collect(Collectors.toList());
        log.info("filteredGrades {}",filteredGrades.toString());
        // 查询学生信息
        Map<Integer, Students> studentMap = iStudentsService.list().stream()
                .collect(Collectors.toMap(Students::getStudentId, student -> student));
        log.info("filteredGrades {}",studentMap.toString());
        // 查询课程信息
        Map<Integer, Courses> courseMap = teacherCourses.stream()
                .collect(Collectors.toMap(Courses::getCourseId, course -> course));
        log.info("courseMap {}",courseMap.toString());
        // 构建 GradeVO 列表
        List<GradeVO> gradeVOList = filteredGrades.stream()
                .map(grade -> {
                    Students student = studentMap.get(grade.getStudentId());
                    Courses course = courseMap.get(grade.getCourseId());

                    GradeVO gradeVO = new GradeVO();
                    gradeVO.setStudentName(student.getName());
                    gradeVO.setCourseName(course.getCourseName());
                    gradeVO.setGrade(grade.getGrade());
                    gradeVO.setGradeDate(grade.getGradeDate());
                    gradeVO.setUpdateTime(grade.getUpdateTime());
                    return gradeVO;
                })
                .collect(Collectors.toList());
        log.info(gradeVOList.toString());
        if (gradeVOList == null) {
            throw new Exception("数据为空");
        }
        return gradeVOList;
    }
}
