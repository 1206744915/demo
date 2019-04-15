package com.demo.sns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import javafx.scene.input.DataFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Slf4j
@TableName("NOTICE")
public class Notice {
    /**
     * id
     */
    @TableField("ID")
    private String id;
    /**
     * 内容
     */
    @TableField("CONTENT")
    private String content;
    /**
     * 发布时间
     */
    @TableField("CREATE_TIME")
    private Timestamp createTime;
    /**
     * 发布人ID
     */
    @TableField("USER_ID")
    private String userId;
    /**
     * 发布人姓名
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 公告类型
     * 0：school   1 class
     */
    @TableField("TYPE")
    private int type;
    /**
     * 班级编号
     */
    @TableField("CLASS_ID")
    private String classId;
    /**
     * 学校编号
     */
    @TableField("SCHOOL_CODE")
    private String schoolCode;

}
