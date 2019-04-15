package com.demo.sns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 权限管理实体类
 */
@Data
@TableName("PERMISSION")
public class Permission {
    /**
     * id
     */
    @TableField("ID")
    private String id;
    /**
     * 权限编码
     */
    @TableField("CODE")
    private String code;
    /**
     * 权限名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 上级权限编码
     */
    @TableField("PARENT_CODE")
    private String parentCode;
    /**
     * 权限类型
     */
    @TableField("TYPE")
    private String type;
    /**
     * 权限url
     */
    @TableField("URL")
    private String url;

    @TableField("DEFAULT_FLAG")
    private String defaultFlag;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @TableField("DISPLAY")
    private int display;
    /**
     * 角色编号
     */
    @TableField("ROLE_CODE")
    private String roleCode;
}
