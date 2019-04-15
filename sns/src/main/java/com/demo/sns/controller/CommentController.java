package com.demo.sns.controller;

import com.demo.sns.entity.Comment;
import com.demo.sns.service.CommentService;
import com.demo.sns.util.controller.AbstractController;
import com.demo.sns.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("comment")
public class CommentController extends AbstractController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/insertComment")
    public Response insertComment(@RequestBody Comment comment){
        try {
            commentService.insertComment(comment);
            return returnSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    @GetMapping("/getCommentByTalkingTipId")
    public Response getCommentByTalkingTipId(String talkingTipId){
        try {
            List<Comment> result = commentService.getCommentByTalkingTipId(talkingTipId);
            return returnSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
}
