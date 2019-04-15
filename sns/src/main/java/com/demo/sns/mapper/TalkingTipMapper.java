package com.demo.sns.mapper;

import com.demo.sns.entity.TalkingTip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态Mapper
 */
@Mapper
public interface TalkingTipMapper {
    /**
     * 根据用户Id获取动态,并且按照最新时间进行排序
     * @param userId
     * @return
     */
    List<TalkingTip> getTalkingTipByUserIddesc(@Param("userId") String userId);

    /**
     * 获取所有的动态，用于动态浏览，并且按照最新时间进行排序
     * @return
     */
   List<TalkingTip> getAllTalkingTipdesc();

    /**
     * 发表动态
     * @param talkingTip
     */
   int insertTalkingTip(@Param("talkingTip") TalkingTip talkingTip);
    /**
     * 删除动态
     */
    int deleteTalkingTipById(@Param("talkingTipId") String talkingTipId);
    /**
     * 对用户的动态进行敏感词检查
     */
    List<TalkingTip> getIlleagleTalkingTip();
}
