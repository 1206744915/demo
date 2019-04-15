package com.demo.sns.entity;

import lombok.Data;

@Data
public class Message {
    /**
     * id
     */
    private String id;
    /**
     * 消息来自哪里
     */
    private User User;
    /**
     * 消息内容
     */
    private String context;
    /**
     * 消息发送时间
     */
    private String time;
}
