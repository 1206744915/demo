package com.demo.sns.util.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageResponse extends Response implements Serializable {
    /**
     * 一共有多少条数据
     */
    private long totalCount;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 总页数
     */
    private int pageSize;
}
