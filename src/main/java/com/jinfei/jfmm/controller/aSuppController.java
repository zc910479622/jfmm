package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.model.aSupp;
import com.jinfei.jfmm.services.IASuppService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Basic/Supp")
public class aSuppController  extends BaseException {

    private String prefix = "/Basic/Supp";

    @Resource
    private IASuppService iaSuppService;

    @RequiresPermissions("Basic:Supp:view")
    @RequestMapping("")
    public String supp(){
        return prefix+"/supp";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<aSupp> list(aSupp aSupp){
        return iaSuppService.list(aSupp);
    }

    //跳转新增页面
    @RequestMapping(value = {"/add"})
    public String add() {
        return "Basic/Supp/add";
    }

    //新增保存
    @RequiresPermissions("Basic:Supp:add")
    @Log(title = "供应商管理", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/addSave"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(aSupp aSupp) {
        try {
            if (iaSuppService.insertSupp(aSupp)) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            return AjaxResult.error();
        }

    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, HttpServletRequest request) {
        request.setAttribute("supp", iaSuppService.getSupp(fid));
        return "Basic/Supp/edit";
    }

    //修改保存
    @RequiresPermissions("Basic:Supp:edit")
    @Log(title = "供应商管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editSave(aSupp aSupp) {
        if (iaSuppService.updateSupp(aSupp)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequiresPermissions("Basic:Supp:remove")
    @Log(title = "供应商管理", businessType = BusinessType.DELETE)
    @RequestMapping(value = {"/remove/{fId}"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(@PathVariable("fId") String fId) {
        if (iaSuppService.delete(fId)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @RequestMapping("/checkFName")
    @ResponseBody
    public String checkFName(@RequestParam String fName, String fId) throws IOException {
        String jsonResult = "";

        if (fName != null) {
            boolean b = this.iaSuppService.checkFName(fName,fId);
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
