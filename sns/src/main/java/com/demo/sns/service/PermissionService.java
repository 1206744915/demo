package com.demo.sns.service;

import com.demo.sns.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissionByRoleCode(String roleCode);
}
