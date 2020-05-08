package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.dao.RoleMapper;
import com.jinfei.jfmm.dao.UserMapper;
import com.jinfei.jfmm.model.Role;
import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.services.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceIml implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public List<Role> selectRolesByUserId(String userId) {
        List<Role> roles = selectRoleAll();
        User u  = userMapper.selectByPrimaryKey(userId);
        String[] arr = u.getRoleId().split(",");
        for (Role role : roles)
        {
            if (!StringUtils.isNull(arr)) {
                for (String s : arr) {
                    if (s.equals(role.getRoleId())) {
                        role.setFlag(true);
                        break;
                    }
                }
            }
        }
        return roles;
    }

    @Override
    public List<Role> list(Role role) {
        return roleMapper.list(role);
    }

    @Override
    public Role getRole(String roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public Boolean insertRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public boolean delete(String roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

}
