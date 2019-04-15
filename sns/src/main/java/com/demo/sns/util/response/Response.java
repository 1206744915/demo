package com.demo.sns.util.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回对象的实体类
 */
@Data
public class Response implements Serializable {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 是否业务异常
     */
    private boolean businessException;
    /**
     * 状态码
     */
    private String errorCode;
    /**
     * 信息
     */
    private String message;
    /**
     * 返回结果
     */
    private Object result;
}
