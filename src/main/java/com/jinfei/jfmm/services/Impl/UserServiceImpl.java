package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.common.exception.CustomException;
import com.jinfei.jfmm.common.utils.DateUtils;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.dao.UserMapper;
import com.jinfei.jfmm.framework.shiro.service.passwordService;
import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.services.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private passwordService passwordService;

    @Override
    public User login(String username, String password) throws CustomException {
        User user = userMapper.selectUser(username);
        if (StringUtils.isNull(user)){
            throw new CustomException("用户不存在");
        }else if (passwordService.matches(user,password)){
            user.setLoginDate(DateUtils.getNowDate());
            user.setLoginIp(shiroUtil.getIp());
            userMapper.setLoginLastTime(user);
            return user;
        }else {
            throw new CustomException("密码错误");
        }
    }

    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public boolean insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean resetUserPwd(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean deleteUserByIds(String ids) {
        return userMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public boolean checkLoginName(String loginName, String userId) {
        return userMapper.checkLoginName(loginName,userId);
    }

    @Override
    public List<String> getGroupUser(String roleId) {
        return userMapper.getGroupUser(roleId);
    }

    @Override
    public List<String> getGroupUserKey(String key) {
        return userMapper.getGroupUserKey(key);
    }
}
