package com.group5.hawadeeleasemanagementsystem.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杜小龙
 * @since 2022-03-02
 */
@Data

@TableName("department")
    public class DepartmentEntity implements Serializable {


    @TableId("department_id")
    private Integer departmentId;

    @TableField("department_name")
    private String departmentName;

    @TableField("chairman_number")
    private String chairmanNumber;


}
