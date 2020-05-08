package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.model.Staff;
import com.jinfei.jfmm.services.IStaffService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = {"/Basic/Staff"})
@Controller
public class StaffController  extends BaseException {

    private String prefix = "/Basic/Staff";

    @Resource
    private IStaffService staffService;

    @RequiresPermissions("Basic:Staff:view")
    @RequestMapping("")
    public String staff(){
        return prefix+"/staff";
    }

    @RequestMapping(value = {"selectAll"})
    @ResponseBody
    public List<Staff> selectAll(Staff staff){
        return staffService.selectAll(staff);
    }

//    跳转新增页面
    @RequestMapping(value = {"/add"})
    public String add(){
        return "Basic/Staff/add";
    }
    //新增数据
    @RequestMapping(value = {"/addSave"}, method=RequestMethod.POST)
    @Log(title = "职员管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public AjaxResult addSave(Staff staff){
        staff.setF_id(UUID.randomUUID().toString());
        int success = 0;
        try {
            success = staffService.add(staff);
        }catch (Exception e){
            e.printStackTrace();
            success = 0;
        }

        if (success>0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"},method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, ModelMap modelMap){
        Staff staff = new Staff();
        staff.setF_id(fid);
        modelMap.addAttribute("staff",staffService.selectAll(staff).get(0));
        return "Basic/Staff/edit";
    }

    //执行修改业务
    @RequestMapping(value = {"/edit"}, method=RequestMethod.POST)
    @Log(title = "职员管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult edit(Staff staff){
        int success = 0;
        try {
            success = staffService.edit(staff);
        }catch (Exception e){
            e.printStackTrace();
            success = 0;
        }
        if (success>0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    //移除数据
    @RequestMapping(value = {"/remove"}, method=RequestMethod.POST)
    @Log(title = "职员管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult remove(String ids){
        String[] idss = ids.split(",");
        int success = 0;
        try {
            success = staffService.remove(idss);
        }catch (Exception e){
            e.printStackTrace();
            success = 0;
        }
        if (success>0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }


}
