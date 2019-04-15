package com.demo.sns.mapper;

import com.demo.sns.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /**
     * 获取所有的班级公告
     * @param start
     * @param end
     * @param classId
     * @return
     */
    List<Notice> getClassNoticeOrderByCreateTimeDESC(@Param("start") int start, @Param("end") int end,@Param("type")int type,@Param("classId") String classId);
    /**
     *
     */
    List<Notice> getSchoolNoticeOrderByCreateTimeDESC(@Param("start") int start, @Param("end") int end,@Param("schoolId") String schoolId);
    /**
     *
     * @param notice
     * @return
     */

    int insertNotice(@Param("notice") Notice notice);

    Notice getNoticeById(@Param("id") String id);

    int getNoticeCount();


    int getClassNoticeCount(@Param("classId") String classId);


    int getSchoolNoticeCount(@Param("schoolId") String schoolId);
}
