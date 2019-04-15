package com.demo.sns.service.impl;

import com.demo.sns.entity.Comment;
import com.demo.sns.entity.TalkingTip;
import com.demo.sns.mapper.TalkingTipMapper;
import com.demo.sns.service.CommentService;
import com.demo.sns.service.TalkingTipService;
import com.demo.sns.util.ArgumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TalkingTipServiceImpl implements TalkingTipService {
    @Autowired
   private TalkingTipMapper mapper;
    @Autowired
    private CommentService commentService;
    @Override
    public List<TalkingTip> getTalkingTipByUserIddesc(String userId) {
        ArgumentUtil.hasText(userId,"用户Id不能为空！");
        List<TalkingTip> tips = mapper.getTalkingTipByUserIddesc(userId);
        log.info("List<TalkingTip>====>{}",tips);
        if(tips.size()>0){
            tips.forEach(tip->{
                List<Comment> comments = commentService.getCommentByTalkingTipId(tip.getId());
                log.info("List<Comment>====>{}",comments);
                tip.setComments(comments);
            });
        }
        return tips;
    }

    @Override
    public List<TalkingTip> getAllTalkingTipdesc() {
        List<TalkingTip> tips = mapper.getAllTalkingTipdesc();
        log.info("List<TalkingTip>====>{}",tips);
        if(tips.size()>0){
            tips.forEach(tip->{
                List<Comment> comments = commentService.getCommentByTalkingTipId(tip.getId());
                log.info("List<Comment>====>{}",comments);
                tip.setComments(comments);
            });
        }
        return tips;
    }

    @Override
    public int insertTalkingTip(TalkingTip talkingTip) {
        int result = -1;
        if(null == talkingTip) return result;
        result = mapper.insertTalkingTip(talkingTip);
        return result;
    }
    @Override
    public int deleteTalkingTipById(String talkingTipId) {
        ArgumentUtil.hasText(talkingTipId,"ID不能为空！");
        return mapper.deleteTalkingTipById(talkingTipId);
    }
}
