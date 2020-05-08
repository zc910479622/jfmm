package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    boolean deleteByPrimaryKey(String userId);

    int insert(User record);

    boolean insertSelective(User record);

    User selectByPrimaryKey(String userId);

    boolean updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUser(String username);

    List<User> selectUserList(User user);

    void setLoginLastTime(User user);

    boolean checkLoginName(@Param("loginName") String loginName, @Param("userId") String userId);

    List<String> getGroupUser(String roleId);

    List<String> getGroupUserKey(String key);
}