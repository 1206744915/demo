package com.demo.sns.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.demo.sns.entity.Class;

/**
 * 班级mapper接口
 */
@Mapper
public interface ClassMapper {
    /**
     * 分页获取所有数据库中存在的班级
     * @param start
     * @param end
     * @return
     */
    List<Class> selectAllClassByPage(@Param("start") int start, @Param("end") int end);
    /**
     * 获取数据库中所有存在的班级
     */
    List<Class> selectAllClass();

    /**
     * 插入班级
     * @param c
     */
    void insertClass(@Param("c") Class c);
    /**
     * 获取班级信息
     */
    Class selectClassByClassId( @Param("classId") String classId,@Param("useful")String useful);


    List<Class> selectClassByUserId(@Param("start")int start, @Param("end") int end, @Param("userId") String userId);

    void updateClass(@Param("c") Class c);


    List<Class> getClassByUseful(@Param("useful")String useful, @Param("start") int start, @Param("end") int end);

    void setClassUseful(@Param("classId")String classId, @Param("useful") String useful);
    void deleteClassByClassId(@Param("classId") String classId);

}