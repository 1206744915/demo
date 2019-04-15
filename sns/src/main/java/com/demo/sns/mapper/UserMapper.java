package com.demo.sns.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.sns.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper {
    /**
     * 登录检查
     * @param user
     * @return
     */
    User getUser(@Param("user") User user);
    /**
     * 根据用户ID获取用户
     */
    User getUserByUserId(@Param("userId") String userId);
    /**
     * 改变用户的在线状态
     */
    void updateUserOnlineByUserId(@Param("userId") String userId);

    /**
     * 更新用户的信息
     * @param user
     */
    void updateUser(@Param("user")User user);

    /**
     * 获取相同班级中的学生
     *
     * @param start
     * @param end
     * @param classId
     * @return
     */
    List<User> selectUserByClassId(@Param("start")int start, @Param("end") int end, @Param("classId") String classId);

    /**
     * 获取一个班级中的学生的总数
     * @param classId
     * @return
     */
    int getUserCountByClassId(@Param("classId") String classId);
    /**
     * 获取数据库中的所有的普通用户
     */
    List<User> selectAllUser();
    /**
     * 增加用户
     */
    int addUser(@Param("user")User user);
    /**
     * 删除用户
     */
    int deleteUser(@Param("user") User user);

    /**
     * 分页获取所有用户的信息
     * @param start
     * @param end
     * @return
     */
    List<User> getAllUser(@Param("start")int start,@Param("end") int end);

    int getAllUserCount();

}
