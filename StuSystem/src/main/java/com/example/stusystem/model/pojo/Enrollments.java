package com.example.stusystem.model.pojo;

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
@TableName("enrollments")
@ApiModel(value="Enrollments对象", description="")
public class Enrollments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "enrollment_id", type = IdType.AUTO)
    private Integer enrollmentId;

    private Integer studentId;

    private Integer courseId;

    private LocalDateTime enrollmentDate;


}
