package com.group5.hawadeeleasemanagementsystem.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杜小龙
 * @since 2022-03-02
 */
@Data
@Accessors(chain = true)
@TableName("contrast")
    public class ContrastEntity implements Serializable {


    @TableId("contrast_id")
    private Integer contrastId;

    @TableField("starter_id")
    private Integer starterId;

    @TableField("law_manager")
    private Integer lawManager;

    @TableField("risk_manager")
    private Integer riskManager;

    @TableField("final_manager")
    private Integer finalManager;

    @TableField("contrast_name")
    private String contrastName;

    @TableField("contrast_description")
    private String contrastDescription;

    @TableField("current_manager")
    private Integer currentManager;


}
