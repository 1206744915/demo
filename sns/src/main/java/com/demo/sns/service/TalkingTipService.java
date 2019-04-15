package com.demo.sns.service;


import com.demo.sns.entity.TalkingTip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TalkingTipService {
    /**
     * 根据用户Id获取动态,并且按照最新时间进行排序
     * @param userId
     * @return
     */
    List<TalkingTip> getTalkingTipByUserIddesc(String userId);

    /**
     * 获取所有的动态，用于动态浏览，并且按照最新时间进行排序
     * @return
     */
    List<TalkingTip> getAllTalkingTipdesc();

    /**
     * 发表动态
     * @param talkingTip
     */
    int insertTalkingTip( TalkingTip talkingTip);
    /**
     * 删除动态
     */
    int deleteTalkingTipById( String talkingTipId);
}
