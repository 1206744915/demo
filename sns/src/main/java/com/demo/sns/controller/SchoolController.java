package com.demo.sns.controller;

import com.demo.sns.util.SchoolEnum;
import com.demo.sns.util.controller.AbstractController;
import com.demo.sns.util.response.Response;
import com.demo.sns.util.response.ResponseBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController extends AbstractController {
    @GetMapping("/checkSchool")
    public Response checkSchool(String schoolCode){
        SchoolEnum s = SchoolEnum.getByCode(schoolCode);
        if(null == s){
            return ResponseBuilder.create().buildBusinessException("学校不存在");
        }else{
            return returnSuccess();
        }
    }
}
