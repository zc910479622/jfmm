package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> selectRoleAll();

    List<Role> selectRolesByUserId(String userId);

    List<Role> list(Role role);

    Role getRole(String roleId);

    Boolean insertRole(Role role);

    boolean updateRole(Role role);

    boolean delete(String roleId);
}
