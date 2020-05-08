package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.Role;

import java.util.List;

public interface RoleMapper {
    boolean deleteByPrimaryKey(String roleId);

    int insert(Role record);

    Boolean insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    boolean updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleAll();

    List<Role> list(Role role);
}