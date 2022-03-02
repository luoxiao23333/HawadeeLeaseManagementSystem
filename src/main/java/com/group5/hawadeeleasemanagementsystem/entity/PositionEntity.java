package com.group5.hawadeeleasemanagementsystem.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杜小龙
 * @since 2022-03-02
 */
@Data
@Repository
public class PositionEntity implements Serializable {

    @TableId("position_id")
    private Integer positionId;

    @TableField("department_id")
    private Integer departmentId;

    @TableField("position_name")
    private String positionName;


}
