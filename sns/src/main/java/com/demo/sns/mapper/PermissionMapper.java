package com.demo.sns.mapper;

import com.demo.sns.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 权限mapper
 */
@Mapper
public interface PermissionMapper {
    /**
     * 根据角色编号获取权限信息
     * @param roleCode
     * @return
     */
    List<Permission> getPermissionByRoleCode(@Param("roleCode") String roleCode);
}
