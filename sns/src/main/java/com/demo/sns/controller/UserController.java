package com.demo.sns.controller;


import com.demo.sns.entity.User;
import com.demo.sns.service.UserService;

import com.demo.sns.util.SchoolEnum;
import com.demo.sns.util.controller.AbstractController;
import com.demo.sns.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;
    private static final String SECURITYKEY = "DEFAULT_SECRET_KEY";
    private static final long EXPIRATION = 30 * 60 * 1000;
    public static final Map<String,User> onlineUser = new HashMap<String,User>();  //存储在线用户
    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public Response login(@RequestBody User user, HttpSession session) {
        log.info("session:{}",session);
        String token = user.getUserId()+user.getPassword()+SECURITYKEY+EXPIRATION;
        User u = null;
        try {
            u = userService.getUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
        //if(null != u) userService.updateUserOnlineByUserId(u.getUserId());
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("token",token);
        result.put("user",u);
        if(null != u){
            onlineUser.put(session.getId(),u);
        }
        return returnSuccess(result);
    }

    /**
     * 根据ID获取用户
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserByUserId")
    public Response getUserByUserId(String userId){
        try {
            return returnSuccess(userService.getUserByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }

    /**
     * 根据用户编号获取用户的信息
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserInfo")
    public Response getUserInfo(String userId){
        return getUserByUserId(userId);
    }
    /**
     * 修改用户信息
     */
    @PostMapping("/updateUser")
    public Response updateUser(@RequestBody User user){
        User u = null;
        try {
            u = userService.updateUser(user);
            return returnSuccess(u);
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 登出
     * @param userId
     * @return
     */
    @PostMapping("/logOut")
    public Response logOut(@RequestBody String userId,HttpSession session){
        log.info("session{}",session);
        onlineUser.remove(session.getId());
        return returnSuccess();
    }

    /**
     * 获取所有用户的信息
     * @return
     */
    @GetMapping("/getAllUser")
    public Response getAllUser(int currentPage,int pageSize){
        List<User> users = userService.getAllUser(currentPage,pageSize);
        return returnSuccess(users,userService.getAllUserCount(),currentPage,pageSize);
    }
    /**
     * 删除用户
     */
    @PostMapping("/deleteUser")
    public Response deleteUser(@RequestBody User user){
        try {
            userService.deleteUser(user);
            return returnSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 注册
     */
    @PostMapping("/signUp")
    public Response signUpUser(@RequestBody User user){
        if(user != null){
            SchoolEnum s = SchoolEnum.getByCode(user.getSchoolCode());
            if(s == null){
                return returnSuccess(1);
            }
        }
        try {
            userService.insertUser(user);
            return returnSuccess(2);
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
}
