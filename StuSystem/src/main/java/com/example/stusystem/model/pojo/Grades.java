package com.example.stusystem.model.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2025-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("grades")
@ApiModel(value="Grades对象", description="")
public class Grades implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "grade_id", type = IdType.AUTO)
    private Integer gradeId;

    private Integer studentId;

    private Integer courseId;

    private BigDecimal grade;

    private LocalDateTime gradeDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
