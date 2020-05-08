package com.jinfei.jfmm.services;

import com.jinfei.jfmm.common.exception.CustomException;
import com.jinfei.jfmm.model.User;

import java.util.List;

public interface IUserService {
//    void updateUserInfo(User user);

    User login(String username, String password) throws CustomException;

    List<User> selectUserList(User user);

    boolean insertUser(User user);

    User selectUserById(String userId);

    boolean updateUser(User user);

    boolean resetUserPwd(User user);

    boolean deleteUserByIds(String ids);

    boolean checkLoginName(String loginName, String userId);

    List<String> getGroupUser(String roleId);

    List<String> getGroupUserKey(String key);
}
