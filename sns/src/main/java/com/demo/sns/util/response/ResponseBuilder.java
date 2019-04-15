package com.demo.sns.util.response;

/**
 * 构建这模式---构建者
 */
public class ResponseBuilder {
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

    public ResponseBuilder setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public ResponseBuilder setBusinessException(boolean businessException) {
        this.businessException = businessException;
        return this;
    }

    public ResponseBuilder setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ResponseBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseBuilder setResult(Object result) {
        this.result = result;
        return this;
    }
    protected ResponseBuilder(){
        super();
    }
    public static ResponseBuilder create(){
        return new ResponseBuilder();
    }
    public Response build(){
        Response response = new Response();
        return this.build(response);
    }
    public Response build(Response response){
        response.setSuccess(this.success);
        response.setBusinessException(this.businessException);
        response.setErrorCode(this.errorCode);
        response.setMessage(this.message);
        response.setResult(this.result);
        return response;
    }
    /***************************************************构建成功************************************************/
    public Response buildSuccess(){
        return buildSuccess(null);
    }
    public Response buildSuccess(Object result){
        return buildSuccess(null,result);
    }
    public Response buildSuccess(String message,Object result){
            return this.setSuccess(true).setBusinessException(false).setMessage(message).setResult(result).build();
    }
    /******************************************业务失败************************************************************/
    public Response buildBusinessException(String message){
        return this.buildBusinessException(message,null);
    }
    public Response buildBusinessException(String message,String errorCode){
        return this.buildBusinessException(errorCode,message,null);
    }
    public Response buildBusinessException(String errorCode,String message,Object result){
        return this.setErrorCode(errorCode).setBusinessException(true).setSuccess(false).setMessage(message).setResult(result).build();
    }
    /******************************************非业务问题*******************************************************/
    public Response buildSystemException(String message){
        return this.buildSystemException(message,null);
    }
    public Response buildSystemException(String message,String errorCode){
        return this.buildSystemException(message,errorCode,null);
    }
    public Response buildSystemException(String message,String errorCode,Object result){
        return this.setErrorCode(errorCode).setResult(result).setMessage(message).setSuccess(false).setBusinessException(true).build();
    }
}
