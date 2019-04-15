package com.demo.sns.service.impl;

import com.demo.sns.entity.Message;
import com.demo.sns.entity.User;
import com.demo.sns.mapper.UserMapper;
import com.demo.sns.service.PermissionService;
import com.demo.sns.service.UserService;
import com.demo.sns.util.ArgumentUtil;
import com.demo.sns.util.GenerateNameFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SimpMessagingTemplate template;
    /**
     * 登录检查
     * @param user
     * @return
     */
    @Override
    public User getUser(User user) throws Exception {
        if(user == null) throw new IllegalArgumentException("信息不能为空！");
        if(null == user.getUserId() || null == user.getPassword()) throw new IllegalArgumentException("用户名或密码不能为空！");
        User result = mapper.getUser(user);
        if(null == result){
            throw new Exception("用户名或者密码错误！");
        }
        return result;
    }

    /**
     * 根据用户名获取用户
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(String userId) throws Exception {
        ArgumentUtil.hasText(userId,"用户不能为空");
        User user = mapper.getUserByUserId(userId);
       /* if(null == user) return user;*/
       /* Role role = roleService.getRoleByRoleCode(user.getRoleCode());*/
        /*if(role == null) return user;*/
      /*  List<Permission> permissions = permissionService.getPermissionByRoleCode(role.getCode());
        Set<String> permissionCodes = new HashSet<String>();
        Set<String> permissionUrls = new HashSet<String>();*/
       /* if(permissions.size()>0){
            permissions.forEach(permission -> {
                permissionCodes.add(permission.getCode());
                permissionUrls.add(permission.getUrl());
            });
        }*/
        return user;
    }

    /**
     * 发送在线用户给客户端
     * @param onlineUser
     */
    @Override
    public void sendOnlineUser(Map<String, User> onlineUser) {
        Map<String,List<User>> msg = new HashMap<String,List<User>>();
        List<User> users = new ArrayList<User>();
        for(Map.Entry<String, User> entry : onlineUser.entrySet()){
            users.add(entry.getValue());
        }
        template.convertAndSend("/topic/onlineUser",users);
    }

    /**
     * 聊天实现
     * @param message
     */
    @Override
    public void sendChat(Message message) {
        log.info("发送聊天内容给客户端：{}",message);
        template.convertAndSend("/topic/chat",message);
    }

    /**
     * 更新用户的班级信息
     * @param user
     */
    @Override
    public User updateUser(User user) throws Exception {
        if(null == user) throw new IllegalArgumentException("用户信息不能为空！");
        mapper.updateUser(user);
        return getUserByUserId(user.getUserId());
    }

    /**
     * 分页查询班级成员的信息
     * @param classId
     * @return
     */
    @Override
    public List<User> getUserByClassId(int currentPage,int pageSize,String classId) {
        ArgumentUtil.hasText(classId,"班级编号不能为空");
        int start = (currentPage-1)*pageSize;
        int end = pageSize;
        return mapper.selectUserByClassId(start,end,classId);
    }
    /**
     * 查询一个班的总人数
     * @param classId
     * @return
     */
    @Override
    public int getUserCount(String classId) {
        ArgumentUtil.hasText(classId,"班级编号不能为空");
        return mapper.getUserCountByClassId(classId);
    }

    /**
     * 分页获取所有的用户信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getAllUser(int currentPage, int pageSize) {
        int start = (currentPage-1)*pageSize;
        int end = pageSize;
        return mapper.getAllUser(start,end);

    }

    @Override
    public long getAllUserCount() {
        return mapper.getAllUserCount();
    }

    /**
     * 删除用户
     * @param user
     */
    @Override
    public void deleteUser(User user) throws Exception {
        ArgumentUtil.isNull(user,"用户信息不能为空！");
        int result = mapper.deleteUser(user);
        if(result == -1) throw new Exception("删除失败！");
    }

    /**
     * 插入用户
     * @param user
     */
    @Override
    public int insertUser(User user) throws Exception {
        ArgumentUtil.isNull(user,"注册用户信息为空！");
        User u = getUserByUserId(user.getUserId());
        if(u != null) throw new Exception("账号已经存在！！");
        if(null == user.getUserName()){
            user.setUserName(GenerateNameFactory.getName());
        }
        user.setRoleCode("9999");
        int result = mapper.addUser(user);
        if(result < 1){
            throw new Exception("注册失败");
        }
        return result;
    }


}
