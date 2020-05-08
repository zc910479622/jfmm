package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("user")
public class UserService {

    @Autowired
    private IUserService userService;

    public List<User> getUserList(){
        return userService.selectUserList(new User());
    }
}
