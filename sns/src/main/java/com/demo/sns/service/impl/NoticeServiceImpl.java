package com.demo.sns.service.impl;

import com.demo.sns.entity.Class;
import com.demo.sns.entity.Notice;
import com.demo.sns.mapper.NoticeMapper;
import com.demo.sns.service.ClassService;
import com.demo.sns.service.NoticeService;
import com.demo.sns.util.ArgumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper mapper;
    @Autowired
    private ClassService classService;

    @Override
    public Map<String, Object> getNoticeOrderByCreateTimeDESC(int currentPage, int pageSize, int type, String typeId) throws Exception {
        ArgumentUtil.hasText(typeId,"班级或者学校的编号为空！");
        int start=currentPage - 1;
        int end = pageSize;
       Map<String,Object> map = new HashMap<String,Object>();
        if(type == 1){
            List<Notice> list =  mapper.getClassNoticeOrderByCreateTimeDESC(start,end,type,typeId);
            int total = mapper.getClassNoticeCount(typeId);
            map.put("total",total);
            map.put("notices",list);
        }else if(type == 0){
            List<Notice> list =  mapper.getSchoolNoticeOrderByCreateTimeDESC(start,end,typeId);
            int total = mapper.getSchoolNoticeCount(typeId);
            map.put("total",total);
            map.put("notices",list);
        }else{
            throw new Exception("参数不和法！");
        }
        return map;
    }

    /**
     * 发布公告
     * @param notice
     * @return
     * @throws Exception
     */
    @Override
    public Notice insertNotice(Notice notice) throws Exception {
        if(null == notice) throw new IllegalArgumentException("公告数据为空");
        if(notice.getClassId() == null) throw new IllegalArgumentException("班级编号不能为空");
        Class c = classService.getClassByClassId(notice.getClassId(),"1");
        if(c == null) throw new Exception("该班级不存在或者审核未通过！！");
        int i = mapper.insertNotice(notice);
        if(i <= 0) throw new Exception("发布公告失败");
        return notice;
    }

    /**
     * 根据公告的ID不哦去公告
     * @param id
     * @return
     */
    @Override
    public Notice getNoticeById(String id) {
        ArgumentUtil.hasText(id,"id不能为空！");
        return mapper.getNoticeById(id);
    }

    /**
     * 获取班级和学校的最新公告
     * @return
     */
    @Override
    public Notice getLastNotice(int type,String typeId) throws Exception {
       /* ArgumentUtil.hasText(typeId,"班级或者学校的编号为空！");*/
        if(type == 1){
            List<Notice> list =  mapper.getClassNoticeOrderByCreateTimeDESC(0,1,type,typeId);
            if(list != null && list.size() > 0){
                return list.get(0);
            }else{
                return null;
            }
        }else if(type == 0){
            List<Notice> list =  mapper.getSchoolNoticeOrderByCreateTimeDESC(0,1,typeId);
            if(list != null && list.size() > 0){
                return list.get(0);
            }else{
                return null;
            }
        }else{
            throw new Exception("参数不和法！");
        }
    }
}
