package com.demo.sns.service;

import com.demo.sns.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    Map<String,Object> getNoticeOrderByCreateTimeDESC(int currentPage, int pageSize,int type, String typeId) throws Exception;

    Notice insertNotice(Notice notice) throws Exception;

    Notice getNoticeById(String id);

    Notice getLastNotice(int type,String typeId) throws Exception;
}
