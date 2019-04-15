package com.demo.sns.service.impl;

import com.demo.sns.entity.User;
import com.demo.sns.mapper.ClassMapper;
import com.demo.sns.service.ClassService;
import com.demo.sns.service.UserService;
import com.demo.sns.util.ArgumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.sns.entity.Class;
import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper mapper;
    @Autowired
    private UserService userService;
    /**
     * 分页获取数据库中的班级信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Class> getAllClass(int currentPage, int pageSize) throws Exception {
        int start = (currentPage-1)*pageSize;
        int end = pageSize;
        List<Class> result = mapper.selectAllClassByPage(start,end);
        //根据用户Id获取班级创建人的信息
        if(result.size()>=1){
            User user = userService.getUserByUserId(result.get(0).getUserId());
                result.forEach((c->{
                    c.setUser(user);
                }));
        }
        return result;
    }
    /**
     * 获取所有数据库中存在的班级信息
     * @return
     */
    @Override
    public List<Class> getAllClass() {
        return mapper.selectAllClass();
    }

    /**
     * 创建班级
     * @param c
     */
    @Override
    public void createClass(Class c) throws Exception {
        if(null == c) throw new IllegalArgumentException("班级信息不能为空");
        Class clazz = mapper.selectClassByClassId(c.getClassId(), "1");
        Class clazz1 = mapper.selectClassByClassId(c.getClassId(),"0");
        if(clazz != null || clazz1 != null){
            throw new Exception("该班级已经存在！请勿重复创建");
        }
        mapper.insertClass(c);
    }

    /**
     * 根据班级编号获取班级信息
     * @param classId
     * @return
     */
    @Override
    public Class getClassByClassId(String classId,String useful) throws Exception {
        ArgumentUtil.hasText(classId,"班级编号不能为空");
        Class clazz = mapper.selectClassByClassId(classId,useful);
        if(clazz != null){
            clazz.setUser(userService.getUserByUserId(clazz.getUserId()));
        }

        return clazz;
    }




    @Override
    public List<User> getUserByClassId(int currentPage, int pageSize, String classId) throws Exception {
        ArgumentUtil.hasText(classId,"班级ID不能为空");
        String useful = "1";
        Class c = mapper.selectClassByClassId(classId,useful);
        if(c == null) throw new Exception("该班级不存在或审核未通过！！");
        List<User> users = userService.getUserByClassId(currentPage,pageSize,classId);
        return users;
    }

    @Override
    public void addClass(User user) throws Exception {
        if(null == user) throw new IllegalArgumentException("用户信息不能为空");
        userService.updateUser(user);
    }

    @Override
    public List<Class> getClassByUserId(int currentPage, int pageSize, String userId) throws Exception {
        ArgumentUtil.hasText(userId,"创建人编号不能为空！");
        int start = (currentPage-1)*pageSize;
        int end = pageSize;
        List<Class> clazzes = mapper.selectClassByUserId(start,end,userId);
        for (Class c : clazzes) {
            c.setUser(userService.getUserByUserId(userId));
        }
        return  clazzes;
    }

    @Override
    public int getClassCount(String userId) {
        ArgumentUtil.hasText(userId,"创建人编号不能为空！");
        List<Class> classes = getAllClass();
        int count = 0;
        for (Class c:classes){
            if(c.getUserId().equals(userId)) count++;
        }
        return count;
    }

    @Override
    public int getClassCount() {
        return mapper.selectAllClass().size();
    }

    @Override
    public void updateClass(Class c) {
        if(c == null) throw new IllegalArgumentException("班级信息不能为空！");
        mapper.updateClass(c);
    }

    @Override
    public List<Class> getClassByUseful(String useful,int currentPage, int pageSize) {
        ArgumentUtil.hasText(useful,"获取班级类型不能为空");
        int start = currentPage -1;
        int end = pageSize;
        return mapper.getClassByUseful(useful,start,end);
    }
    /**
     * 系统管理员审核班级
     */
    @Override
    public void setClassUseful(String classId, String useful) {
        ArgumentUtil.hasText(classId,"班级编号不能为空");
        mapper.setClassUseful(classId,useful);
    }

    /**
     * 删除班级
     * @param classId
     */
    @Override
    public void deleteClassByClassId(String classId) {
        ArgumentUtil.hasText(classId,"班级ID为空！");
        mapper.deleteClassByClassId(classId);
    }
}
