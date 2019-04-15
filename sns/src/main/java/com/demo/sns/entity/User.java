package com.demo.sns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;


@TableName("USER")
@Data
public class User {
    /**
     * ID
     */
    @TableField("ID")
    private String Id;
    /**
     * 用户账号
     */
    @TableField("USERID")
    private String userId;
    /**
     * 用户密码
     */
    @TableField("PASSWORD")

    private String password;
    /**
     * 用户姓名
     */
    @TableField("USERNAME")

    private String userName;
    /**
     * 用户性别
     */
    @TableField("USERSEX")
    private String userSex;
    /**
     * 手机号码
     */
    @TableField("PHONENUMBER")
    private String phoneNumber;
    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;
    /**
     * 班级ID
     */
    @TableField("CLASSID")
    private String classId;
    /**
     * 角色编号
     */
    @TableField("ROLE_CODE")
    private String roleCode;
    /**
     * 学校编号
     */
    @TableField("SCHOOL_CODE")
    private String schoolCode;
}
