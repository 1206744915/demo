package com.demo.sns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * 动态实体
 */
@Data
@Slf4j
@TableName("TALKINGTIP")
public class TalkingTip {
    /**
     * id
     */
    @TableField("ID")
    private String id;
    /**
     * 动态标题
     */
    @TableField("TITLE")
    private String title;
    /**
     * 动态内容
     */
    @TableField("CONTEXT")
    private String context;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 发布人
     */
    @TableField("USER_ID")
    private String UserId;
    /**
     * 评论
     */
    @TableField(exist = false)
    private List<Comment> comments;
}
