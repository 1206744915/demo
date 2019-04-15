package com.demo.sns.service;


import com.demo.sns.entity.Message;
import com.demo.sns.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUser(User user) throws Exception;

    User getUserByUserId(String userId) throws Exception;

    void sendOnlineUser(Map<String,User> onlineUser);
    void sendChat(Message message);

    User updateUser(User user) throws Exception;

    List<User> getUserByClassId(int currentPage, int pageSize, String classId);

    int getUserCount(String classId);

    List<User> getAllUser(int currentPage, int pageSize);

    long getAllUserCount();

    void deleteUser(User user) throws Exception;

    int insertUser(User user) throws Exception;

}
