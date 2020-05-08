package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.DateUtils;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.services.IRoleService;
import com.jinfei.jfmm.services.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Basic/User")
public class UserController extends BaseException {

    private String prefix = "/Basic/User";

    @Resource
    private IUserService userService;

    @RequiresPermissions("Basic:User:view")
    @RequestMapping("")
    public String user(){
        return prefix+"/user";
    }

    @Resource
    private IRoleService roleService;

    @Autowired
    private com.jinfei.jfmm.framework.shiro.service.passwordService passwordService;

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(User user) {
        return userService.selectUserList(user);
    }

    /**
     * 新增用户
     */
    @RequestMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll());
        return "Basic/User/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("Basic:User:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @RequestMapping("/addSave")
    @ResponseBody
    public AjaxResult addSave(User user) {
        if (StringUtils.isNotNull(user.getUserId()) && user.getUserId().equals("0")) {
            return AjaxResult.error("不允许修改超级管理员用户");
        }
        user.setSalt(shiroUtil.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(shiroUtil.getLoginName());
        user.setCreateTime(DateUtils.getNowDate());
        if (userService.insertUser(user)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        return "Basic/User/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("Basic:User:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(User user) {
        if (StringUtils.isNotNull(user.getUserId()) && user.getUserId().equals("0")) {
            return AjaxResult.error("不允许修改超级管理员用户");
        }
        user.setUpdateBy(shiroUtil.getLoginName());
        user.setUpdateTime(DateUtils.getNowDate());
        if (userService.updateUser(user)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    @RequestMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") String userId, ModelMap mmap) {
        mmap.put("user", userService.selectUserById(userId));
        return "Basic/User/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @RequestMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(User user) {
        user.setSalt(shiroUtil.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (userService.resetUserPwd(user)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    @RequiresPermissions("Basic:User:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            if (userService.deleteUserByIds(ids)){
                return AjaxResult.success();
            }else {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequestMapping("/checkLoginName")
    @ResponseBody
    public String checkLoginName(@RequestParam String loginName, String userId) throws IOException {
        String jsonResult = "";

        if (loginName != null) {
            boolean b = this.userService.checkLoginName(loginName,userId);
            if (b) {
                jsonResult = "{\"valid\":false}";
            } else {
                jsonResult = "{\"valid\":true}";
            }
        } else {
            jsonResult = "{\"valid\":false}";
        }
        return jsonResult;
    }
}
