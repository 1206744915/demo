package com.demo.sns.service;

import com.demo.sns.entity.User;
import com.demo.sns.entity.Class;
import java.util.List;

/**
 * 班级服务的接口
 */
public interface ClassService {
    /**
     * 分页查询数据库中的所有班级信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Class> getAllClass(int currentPage, int pageSize) throws Exception;
    /**
     * 获取数据库中存在班级的总数
     */
    int getClassCount();
    /**
     * 创建班级
     * @param c
     */
    void createClass(Class c) throws Exception;

    /**
     * 根据classId获取班级信息
     *
     * @param id
     * @param classId
     * @return
     */
    Class getClassByClassId(String id, String classId) throws Exception;
    /**
     * 获取全部数据库中的班级
     */
    List<Class> getAllClass();


    /**
     * 获取班级中的成员
     *
     * @param currentPage
     * @param pageSize
     * @param classId
     * @return
     */
    List<User> getUserByClassId(int currentPage, int pageSize, String classId) throws Exception;
    /**
     * 用户加入班级
     */
    void addClass(User user) throws Exception;

    List<Class> getClassByUserId(int currentPage, int pageSize, String userId) throws Exception;

    int getClassCount(String userId);



    void updateClass(Class c);

    List<Class> getClassByUseful(String useful,int currentPage, int pageSize);
    void setClassUseful(String classId, String useful);
    void deleteClassByClassId(String classId);
}
