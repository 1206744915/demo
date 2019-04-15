package com.demo.sns.controller;

import com.demo.sns.entity.TalkingTip;
import com.demo.sns.service.TalkingTipService;
import com.demo.sns.util.controller.AbstractController;
import com.demo.sns.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/talkingTip")
public class TalkingTipController extends AbstractController {
    @Autowired
    private TalkingTipService talkingTipService;
    @PostMapping("/insertTalkingTip")
    public Response insertTalkingTip(@RequestBody TalkingTip talkingTip){
        log.info("请求参数：{}",talkingTip);
        try{
            int result = talkingTipService.insertTalkingTip(talkingTip);
            if(result>=1){
                return returnSuccess();
            }else{
                return returnException("动态发表失败！");
            }
        }catch(Exception e){
            return returnException(e.getMessage());
        }
    }
    @GetMapping("/deleteTalkingTipById")
    public Response deleteTalkingTipById(String talkingTipId){
        log.info("请求参数：{}",talkingTipId);
        try{
            int result = talkingTipService.deleteTalkingTipById(talkingTipId);
            if(result>=1){
                return returnSuccess();
            }else{
                return returnException("动态删除存在异常，请稍后重试！");
            }
        }catch(Exception e){
            return returnException(e.getMessage());
        }

    }
    @GetMapping("/getTalkingTipByUserId")
    public Response getTalkingTipByUserId(String userId){
        log.info("请求参数：{}",userId);
        try{
            List<TalkingTip> tps= talkingTipService.getTalkingTipByUserIddesc(userId);
            return returnSuccess(tps);
        }catch(Exception e){
            return returnException(e.getMessage());
        }
    }
    @GetMapping("/getAllTalkingTip")
    public Response getAllTalkingTipdesc(){
        log.info("======获取所有的动态并将动态按照最新时间顺序排序");
        try{
            List<TalkingTip> tps= talkingTipService.getAllTalkingTipdesc();
            return returnSuccess(tps);
        }catch(Exception e){
            return returnException(e.getMessage());
        }
    }

}
