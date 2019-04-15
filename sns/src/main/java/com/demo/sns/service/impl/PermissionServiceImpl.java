package com.demo.sns.service.impl;

import com.demo.sns.entity.Permission;
import com.demo.sns.mapper.PermissionMapper;
import com.demo.sns.service.PermissionService;
import com.demo.sns.util.ArgumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper mapper;
    @Override
    public List<Permission> getPermissionByRoleCode(String roleCode) {
        ArgumentUtil.hasText(roleCode,"角色编码不能为空！");
        return mapper.getPermissionByRoleCode(roleCode);
    }
}
