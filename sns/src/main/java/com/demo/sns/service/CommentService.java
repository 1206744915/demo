package com.demo.sns.service;

import com.demo.sns.entity.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 评论
     * @param comment
     */
    void insertComment(Comment comment) throws Exception;
    /**
     * 根据动态Id 获取评论
     */
    List<Comment> getCommentByTalkingTipId(String talkingTipId);
}
