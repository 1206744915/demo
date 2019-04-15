package com.demo.sns.mapper;

import com.demo.sns.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 根据动态的Id获取评论
     * @param talkingTipId
     * @return
     */
    List<Comment> getCommentByTalkingTipId(@Param("talkingTipId") String talkingTipId);

    /**
     * 写入评论
     * @param comment
     */
    int insertComment(@Param("comment") Comment comment);
    /**
     * 对用户的评论进行敏感词检查
     */
    List<Comment> getIlleagleComment();
}
