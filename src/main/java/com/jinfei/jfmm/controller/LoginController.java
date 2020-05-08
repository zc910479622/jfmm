package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.utils.DateUtils;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.services.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private IUserService iUserService;

    @RequestMapping("login")
    public String log(){
        return "login";
    }

    @RequestMapping("/login.action")
    @ResponseBody
    public AjaxResult login(User user) throws Exception{
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return AjaxResult.success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return AjaxResult.error(msg);
        }
    }

    /**
     * 记录登录信息
     */
    /*public void recordLoginInfo(User user)
    {
        user.setLoginIp(shiroUtil.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        IUserService.updateUserInfo(user);
    }*/
}
