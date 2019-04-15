package com.demo.sns.service.impl;

import com.demo.sns.entity.Comment;
import com.demo.sns.mapper.CommentMapper;
import com.demo.sns.service.CommentService;
import com.demo.sns.util.ArgumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
   private CommentMapper mapper;

    /**
     * 评论
     * @param comment
     * @throws Exception
     */
    @Override
    public void insertComment(Comment comment) throws Exception {
        if(null == comment) throw new IllegalArgumentException("评论不能为空！");
        ArgumentUtil.hasText(comment.getTalkingTipId(),"动态ID不能为空！");
        ArgumentUtil.hasText(comment.getUserId(),"用户ID不能为空！！");
        ArgumentUtil.hasText(comment.getContext(),"评论内容不能为空");
        int result = mapper.insertComment(comment);
        if(result <= 0) throw new Exception("评论失败！");
    }

    /**
     * 根据动态ID获取评论
     * @param talkingTipId
     * @return
     */
    @Override
    public List<Comment> getCommentByTalkingTipId(String talkingTipId) {
        ArgumentUtil.hasText(talkingTipId,"动态ID不能为空");
        return mapper.getCommentByTalkingTipId(talkingTipId);
    }
}
