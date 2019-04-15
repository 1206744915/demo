package com.demo.sns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 评论实体
 */
@Data
@Slf4j
@TableName("COMENT")
public class Comment {
    /**
     * id
     */
    @TableField("ID")
    private String id;
    /**
     * 动态Id
     */
    @TableField("TALKINGTIP_ID")
    private String talkingTipId;
    /**
     * 评论人ID
     */
    @TableField("USER_ID")
    private String userId;
    /**
     * 评论内容
     */
    @TableField("CONTEXT")
    private String context;
    /**
     * 评论时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;
}
