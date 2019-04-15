package com.demo.sns.service.impl;

import com.demo.sns.entity.Comment;
import com.demo.sns.entity.TalkingTip;
import com.demo.sns.entity.User;
import com.demo.sns.mapper.CommentMapper;
import com.demo.sns.mapper.TalkingTipMapper;
import com.demo.sns.mapper.UserMapper;
import com.demo.sns.service.SuperManagerService;
import com.demo.sns.util.ArgumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SuperManagerServiceImpl implements SuperManagerService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TalkingTipMapper talkingTipMapper;
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        ArgumentUtil.isNull(user,"用户信息不能为空！");
        userMapper.addUser(user);
    }

    /**
     * 删除用户
     * @param user
     */
    @Override
    public void deleteUser(User user) {
        ArgumentUtil.isNull(user,"用户的信息不能为空！");
        userMapper.deleteUser(user);
    }

    /**
     * 获取所有的普通用户
     * @return
     * @param currentPage
     * @param pageSize
     */
    @Override
    public List<User> getUser(int currentPage, int pageSize) {
        return userMapper.selectAllUser();
    }

    /**
     * 设置用户为管理员
     */
    @Override
    public void updateUserRole(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 对用户发表的动态进行敏感词的过滤
     * @return
     */
    @Override
    public List<TalkingTip> getIlleagleTalkingTip() {
        return talkingTipMapper.getIlleagleTalkingTip();
    }

    /**
     * 对用户的评论进行敏感词的过滤
     * @return
     */
    @Override
    public List<Comment> getIlleagleComment() {
        return commentMapper.getIlleagleComment();
    }
}
