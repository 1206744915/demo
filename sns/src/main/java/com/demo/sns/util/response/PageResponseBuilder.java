package com.demo.sns.util.response;
/**
 * PageResponse的构建器
 */
public class PageResponseBuilder extends ResponseBuilder {
    /**
     * 数据总条数
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
    private PageResponseBuilder() {
        super();
    }
    public PageResponseBuilder setTotalCount(long totalCount){
        this.totalCount = totalCount;
        return this;
    }
    public PageResponseBuilder setCurrentPage(int currentPage){
        this.currentPage = currentPage;
        return this;
    }
    public PageResponseBuilder setPageSize(int pageSize){
        this.pageSize = pageSize;
        return this;
    }

    public static PageResponseBuilder create(){
        return new PageResponseBuilder();
    }
    public PageResponse build(){
        PageResponse response = new PageResponse();
        super.build(response);
        response.setCurrentPage(this.currentPage);
        response.setPageSize(this.pageSize);
        response.setTotalCount(this.totalCount);
        return response;
    }
    public PageResponse buildSuccess(Object result,long totalCount,int currentPage,int pageSize){
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
        return (PageResponse)super.buildSuccess(result);
    }
}
