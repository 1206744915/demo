package com.demo.sns.intecepter;

import com.demo.sns.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 *频道拦截器
 */
@Slf4j
public class SocketChannelIntecepter implements ChannelInterceptor {
    /**
     * 消息发送到管道之前调用
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("SocketChannelIntecepter=====>preSend");
        return null;
    }

    /**
     * 消息发送之后调用，无论是否发生异常，都会调用，常用于做一些资源释放清理工作
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        log.info("SocketChannelIntecepter=====>afterSend");
    }

    /**
     * 发送消息调用后立刻调用此方法
     * @param message
     * @param channel
     * @param sent
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
       log.info("SocketChannelIntecepter->postSend");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);//消息头访问器
        if (headerAccessor.getCommand() == null ) return ;// 避免非stomp消息类型，例如心跳检测
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        log.info("SocketChannelIntecepter -> sessionId = "+sessionId);
        switch (headerAccessor.getCommand()) {
            case CONNECT:
                connect(sessionId);
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:

                break;

            case UNSUBSCRIBE:

                break;
            default:
                break;
        }
    }
    private void connect(String sessionId){
        log.info("CONNECT SUCCESS=====>sessionId = {}",sessionId);
    }
    private void disconnect(String sessionId){
        log.info("DISCONNECT SUCCESS===>sessionId = {}",sessionId);
        UserController.onlineUser.remove(sessionId);
    }

}
