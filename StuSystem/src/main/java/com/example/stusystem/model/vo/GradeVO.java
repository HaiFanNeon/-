package com.example.stusystem.model.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GradeVO {
    private String studentName;
    private String courseName;
    private BigDecimal grade;
    private LocalDateTime gradeDate;
    private LocalDateTime updateTime;
}
