package com.demo.sns.entity;

import lombok.Data;

import java.util.Date;

/**
 * 聊天实体
 */
@Data
public class Chat {
    /**
     * 聊天内容
     */
    private String content;
    /**
     * 用户Id
     */
    private String UserId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 创建时间
     */
    private String createTime;
}
