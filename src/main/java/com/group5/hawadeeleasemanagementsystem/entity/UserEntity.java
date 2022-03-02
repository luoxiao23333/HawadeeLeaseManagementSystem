package com.group5.hawadeeleasemanagementsystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("user")
public class UserEntity {
    /*
    员工id
     */
    /*
    变量名不能有下划线，如employee_id的属性名为employeeid
     */
    @TableId("user_id")
    private String userid;
    /*
    部门id
     */
    @TableField("department_id")
    private String departmentid;
    /*
    职位id
     */
    @TableField("position_id")
    private String positionid;
    /*
    员工名字
     */
    @TableField("user_name")
    private String username;

    /*
    性别
     */
    @TableField("sex")
    private String sex;
    /*
    手机号
     */
    @TableField("phone")
    private String phone;
    /*
    邮箱
     */
    @TableField("email")
    private String email;
    /*
    密码
     */
    @TableField("password")
    private String password;
}
