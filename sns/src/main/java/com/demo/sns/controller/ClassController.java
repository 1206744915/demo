package com.demo.sns.controller;

import com.demo.sns.entity.User;
import com.demo.sns.service.ClassService;
import com.demo.sns.service.UserService;
import com.demo.sns.util.SchoolEnum;
import com.demo.sns.util.controller.AbstractController;
import com.demo.sns.util.response.Response;
import com.demo.sns.util.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.demo.sns.entity.Class;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/class")
public class ClassController extends AbstractController {
    @Autowired
    private ClassService classService;
    @Autowired
    private UserService userService;
    /**
     *分页获取数据库中存在的班级信息
     */
    @GetMapping("/getClass")
    public Response getAllClass(int currentPage,int pageSize){
        try {
            return returnSuccess(classService.getAllClass(currentPage,pageSize),classService.getAllClass().size(),currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 创建班级
     */
    @PostMapping("/createClass")
    public Response createClass(@RequestBody Class c){
        try {
            classService.createClass(c);
            return returnSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 根据班级编号获取班级
     */
    @GetMapping("getClassByClassId")
    public Response getClassByClassId(String classId,String useful){
        try {
            return returnSuccess(classService.getClassByClassId(classId,useful));
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 获取班级中的用户
     */
    @GetMapping("/getUserByClassId")
    public Response getUserByClassId(int currentPage, int pageSize, String classId){
        try {
            return returnSuccess(classService.getUserByClassId(currentPage,pageSize,classId),userService.getUserCount(classId),currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }

    }
    /**
     * 用户加入班级
     */
    @PostMapping("/addClass")
    public Response addClass(@RequestBody User user) throws Exception {
        try{
            if(user.getPassword() == null){
                User u = userService.getUserByUserId(user.getUserId());
                user.setPassword(u.getPassword());
            }
            User u =userService.updateUser(user);
            if(null != u &&u.getId() != null){
                return returnSuccess(u);
            }else{
                return ResponseBuilder.create().buildBusinessException("加入班级失败！请检查信息是否正确");
            }
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }


    }
    /**
     *删除班级中的成员
     */
    @PostMapping("/removeUser")
    public Response removeUser(User user){
        try {
            userService.updateUser(user);
            return returnSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }

    }
    /**
     * 根据创建人编号获取班级信息
     */
    @GetMapping("/getClassByUserId")
    public Response getClassByUserId(int currentPage,int pageSize, String userId){
        try {
            return returnSuccess(classService.getClassByUserId(currentPage,pageSize,userId),classService.getClassCount(userId),currentPage,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 修改班级信息
     */
    @PostMapping("/updateClass")
    public Response updateClass(@RequestBody Class c){
        try {
            classService.updateClass(c);
            return returnSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 获取等待审核的班级信息
     */
    @GetMapping("/getClassByUseful")
    public Response getClassByUseful(String useful, int currentPage,int pageSize){
        try {
            List<Class> result = classService.getClassByUseful(useful,currentPage,pageSize);
            int count = classService.getClassCount();
            return returnSuccess(result,count,currentPage,pageSize);
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 审核班级
     */
    @GetMapping("/check")
    public Response checkClass(String schoolCode){
        try {
            SchoolEnum s = SchoolEnum.getByCode(schoolCode);
            if(s == null){
                return returnSuccess(-1);
            }else{
                Map<String,String> map = new HashMap<>();
                map.put("code",s.getCode());
                map.put("name",s.getValue());
                return returnSuccess(map);
            }
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    //系统管理员审核班级
    @GetMapping("/setClassUseful")
    public Response setClassUseful(String classId,String useful){
        try {
            classService.setClassUseful(classId,useful);
            return returnSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return returnException(e.getMessage());
        }
    }
    /**
     * 删除班级
     */
    @GetMapping("/deleteClassByClassId")
    public Response deleteClassByClassId(String classId){
        try{
            classService.deleteClassByClassId(classId);
            return returnSuccess();
        }catch(Exception e){
            return returnException(e.getMessage());
        }
    }
}
