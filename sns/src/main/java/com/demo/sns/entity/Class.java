package com.demo.sns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 班级实体类
 */
@Data
@TableName("CLASS")
public class Class {
    /**
     * id
     */
    @TableField("ID")
    private String id;
    /**
     * 班级编号
     */
    @TableField("CLASS_ID")
    private String classId;
    /**
     * 管理员
     */
    @TableField("USER_ID")
    private String userId;
    /**
     * 管理员的全部信息
     */
    @TableField(exist = false)
    private User user;
    /**
     * 班级名称
     */
    @TableField("CLASS_NAME")
    private String className;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private String createTime;
    /**
     * 班级审核状态
     */
    @TableField("USEFUL")
    private String useful;
    /**
     * 班级所属的学校
     */
    @TableField("SCHOOL_CODE")
    private String schoolCode;

}
