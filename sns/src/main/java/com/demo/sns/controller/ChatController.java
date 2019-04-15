package com.demo.sns.controller;

import com.demo.sns.entity.Chat;
import com.demo.sns.entity.User;
import com.demo.sns.util.ArgumentUtil;
import com.demo.sns.util.controller.AbstractController;
import com.demo.sns.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/chat")
public class ChatController extends AbstractController {
    public static final List<Chat> chat = new ArrayList<Chat>();        //聊天内容
    /**
     * 获取所有的在线用户的信息
     * @return
     */
    @GetMapping("/getOnlineUser")
    public Response getOnlineUser(){
       try{
           List<User> users = new ArrayList<User>();
           for(Map.Entry<String,User> entry : UserController.onlineUser.entrySet()){
               users.add(entry.getValue());
           }
           return returnSuccess(users);
       }catch(Exception e){
           return returnException(e.getMessage());
       }
    }

    /**
     * 获取聊天内容
     * @return
     */
    @GetMapping("/getChat")
    public Response getChat(){
        try{
            return returnSuccess(chat);
        }catch(Exception e){
            return returnException(e.getMessage());
        }
    }
    /**
     * 插入聊天内容
     */
    @PostMapping("/insertChat")
    public Response insertChat(@RequestBody Chat c, HttpSession session){
       try{
           ArgumentUtil.isNull(c,"聊天内容不能为空");
           chat.add(c);
           return returnSuccess();
       }catch(Exception e){
           return returnException(e.getMessage());
       }
    }
}
