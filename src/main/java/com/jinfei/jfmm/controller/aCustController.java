package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.model.aCust;
import com.jinfei.jfmm.services.IACustService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Basic/Cust")
public class aCustController extends BaseException {

    private String prefix = "/Basic/Cust";

    @Resource
    private IACustService iaCustService;

    @RequiresPermissions("Basic:Cust:view")
    @RequestMapping("")
    public String cust(){
        return prefix+"/cust";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<aCust> list(aCust aCust){
        return iaCustService.list(aCust);
    }

    //跳转新增页面
    @RequestMapping(value = {"/add"})
    public String add() {
        return "Basic/Cust/add";
    }

    //新增保存
    @RequiresPermissions("Basic:Cust:add")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/addSave"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(aCust aCust) {
        try {
            if (iaCustService.insertCust(aCust)) {
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
        request.setAttribute("cust", iaCustService.getCust(fid));
        return "Basic/Cust/edit";
    }

    //修改保存
    @RequiresPermissions("Basic:Cust:edit")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editSave(aCust aCust) {

        if (iaCustService.updateCust(aCust)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequiresPermissions("Basic:Cust:remove")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
    @RequestMapping(value = {"/remove/{fId}"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(@PathVariable("fId") String fId) {
        if (iaCustService.delete(fId)) {
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
            boolean b = this.iaCustService.checkFName(fName,fId);
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
