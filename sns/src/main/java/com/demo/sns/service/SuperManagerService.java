package com.demo.sns.service;

import com.demo.sns.entity.Comment;
import com.demo.sns.entity.TalkingTip;
import com.demo.sns.entity.User;

import java.util.List;

public interface SuperManagerService {
    /**
     * 添加用户
     */
    void addUser(User user);
    /**
     * 删除用户
     */
    void deleteUser(User user);
    /**
     * 获取全部的普通用户
     * @param currentPage
     * @param pageSize
     */
    List<User> getUser(int currentPage, int pageSize);
    /**
     * 设置用户为管理员
     */
    void updateUserRole(User user);
    /***********对用户发表的动态进行管理*****************/
    List<TalkingTip> getIlleagleTalkingTip();
    /**
     * 对用户发表的评论进行审核
     */
    List<Comment> getIlleagleComment();

}
