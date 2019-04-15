package com.demo.sns.util.controller;

import com.demo.sns.entity.Class;
import com.demo.sns.util.response.*;

/**
 * Controller的基类
 */
public class AbstractController {
    public Response returnSuccess(){
        return this.returnSuccess(null);
    }
    public Response returnSuccess(Object result){
        return ResponseBuilder.create().buildSuccess(result);
    }
    public PageResponse returnSuccess(Object result, long totalCount, int pageSize, int currentPage){
        return PageResponseBuilder.create().buildSuccess(result,totalCount,pageSize,currentPage);
    }
    public Response returnException(String message){
        return ResponseBuilder.create().buildBusinessException(message);
    }
}
