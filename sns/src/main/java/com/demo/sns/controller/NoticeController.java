package com.demo.sns.controller;

import com.demo.sns.entity.Notice;
import com.demo.sns.entity.User;
import com.demo.sns.service.NoticeService;
import com.demo.sns.service.UserService;
import com.demo.sns.util.controller.AbstractController;
import com.demo.sns.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
@Slf4j
public class NoticeController extends AbstractController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;
    /**
     * 获取最新的学校和班级的公告
     * @param classId
     * @return
     */
    @GetMapping("/getLastNotice")
    public Response getLastNotice(String classId,Integer type,String schoolCode){
        try{
            if(type == 1){
                if(classId == null){
                    return returnSuccess(2);
                }
                return returnSuccess(noticeService.getLastNotice(1,classId));
            }else if(type ==0){
                if(schoolCode == null){
                    return returnSuccess(3);
                }
                return returnSuccess(noticeService.getLastNotice(0,schoolCode));
            }else if(type == 2){
                if(classId == null && schoolCode == null){
                    return returnSuccess(4);
                }
                List<Notice> notices = new ArrayList<Notice>();
                Notice r = noticeService.getLastNotice(1,classId);
                if(r != null && r.getId() != null){
                    notices.add(r);
                }
                r = noticeService.getLastNotice(0,schoolCode);
                if(r != null && r.getId() != null){
                    notices.add(r);
                }
                return returnSuccess(notices);
            }else{
                return returnSuccess(1);
            }
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }

    }
    /**
     * 获取最新的学校公告
     */
    /**
     * 分页查询历史公告(班级和学校)
     * @param currentPage
     * @param pageSize
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("/getNotice")
    public Response getNoticeOrderByCreateTimeDESC(int currentPage,int pageSize,int type,String classId,String schoolCode) {
        log.info("分页查询公告信息pageSize{}=====currentPage:{}",pageSize,currentPage);
        try{
            if(type == 1){
                if(classId == null){
                    return returnSuccess(2);
                }
                Map<String,Object> notices = noticeService.getNoticeOrderByCreateTimeDESC(currentPage,pageSize,1,classId);
                log.info("获取到公告信息：{}",notices);
                return returnSuccess(notices.get("notices"),(int)notices.get("total"),currentPage,pageSize);

            }else if(type == 0){
                if(schoolCode == null){
                    return returnSuccess(3);
                }
                Map<String,Object> notices = noticeService.getNoticeOrderByCreateTimeDESC(currentPage,pageSize,0,schoolCode);
                log.info("获取到公告信息：{}",notices);
                return returnSuccess(notices.get("notices"),(int)notices.get("total"),currentPage,pageSize);
            }else{
                return returnSuccess(1);
            }
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }

    }
    @PostMapping("/insertNotice")
    public Response insertNotice(@RequestBody Notice notice){
        log.info("发布一条公告信息：{}",notice);
        try{
            User u  = userService.getUserByUserId(notice.getUserId());
            if(notice.getType() == 1){
                if(notice.getClassId() == null){
                    notice.setClassId(u.getClassId());
                }
            }else if(notice.getType() == 0){
                notice.setSchoolCode(u.getSchoolCode());
            }
            Notice result = noticeService.insertNotice(notice);
            return returnSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }

    }
    @GetMapping("/getNoticeById")
    public Response getNoticeById(String id){
        log.info("根据ID查找对应的公告：{}",id);
        try{
            return returnSuccess(noticeService.getNoticeById(id));
        }catch (Exception e){
            return returnException(e.getMessage());
        }

    }
}
