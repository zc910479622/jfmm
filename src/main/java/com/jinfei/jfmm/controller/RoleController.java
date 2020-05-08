package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.Role;
import com.jinfei.jfmm.services.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("Basic/Role")
public class RoleController extends BaseException {

    private String prefix = "/Basic/Role";

    @Resource
    private IRoleService roleService;

    @RequiresPermissions("Basic:Role:view")
    @RequestMapping("")
    public String role(){
        return prefix+"/role";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Role> list(Role role){
        Subject subject = shiroUtil.getSubject();
        return roleService.list(role);
    }

    /**
     * 新增角色
     */
    @RequestMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll());

        return prefix+"/add";
    }

    @RequiresPermissions("Basic:Role:add")
    @RequestMapping("/addSave" )
    @ResponseBody
    public AjaxResult addSave(Role role){
        role.setCreateBy(shiroUtil.getLoginName());
        try {
            if (roleService.insertRole(role)){
                return AjaxResult.success();
            }else {
                return AjaxResult.error();
            }
        }catch (Exception e){
            return AjaxResult.error();
        }
    }

    @RequestMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") String roleId,ModelMap mmap){
        mmap.put("role",roleService.getRole(roleId));
        return prefix+"/edit";
    }


    @RequiresPermissions("Basic:Role:edit")
    @RequestMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(Role role){
        role.setCreateBy(shiroUtil.getLoginName());
        try {
            if (roleService.updateRole(role)){
                return AjaxResult.success();
            }else {
                return AjaxResult.error();
            }
        }catch (Exception e){
            return AjaxResult.error();
        }
    }

    //删除

    @RequiresPermissions("Basic:Role:remove")
    @RequestMapping(value = {"/remove"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(String ids){
        if (roleService.delete(ids)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }
}
