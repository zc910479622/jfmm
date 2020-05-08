package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.model.mModel;
import com.jinfei.jfmm.services.IMModelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Mould/Model")
public class mModelController {

    private String prefix = "/Mould/Model";

    @Resource
    private IMModelService imModelService;

    @RequiresPermissions("Mould:Model:view")
    @RequestMapping("")
    public String model(){
        return prefix+"/model";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<mModel> list(mModel mModel){
        return imModelService.list(mModel);
    }

    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public mModel selectByPrimaryKey(mModel mModel){
        return imModelService.selectByPrimaryKey(mModel.getfId());
    }

    //跳转新增页面
    @RequestMapping(value = {"/add"})
    public String add() {
        return "Mould/Model/add";
    }

    //新增保存
    @RequestMapping(value = {"/addSave"}, method = RequestMethod.POST)
    @Log(title = "模具型号管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public AjaxResult addSave(mModel Model) {
        try {
            if (imModelService.insertModel(Model)){
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
        request.setAttribute("mModel", imModelService.getModel(fid));
        return "Mould/Model/edit";
    }

    //修改保存
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @Log(title = "模具型号管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(mModel Model) {

        if (imModelService.updateModel(Model)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequestMapping(value = {"/remove/{fId}"}, method = RequestMethod.POST)
    @Log(title = "模具型号管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("fId") String fId) {
        if (imModelService.delete(fId)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = {"/remove"}, method=RequestMethod.POST)
    @Log(title = "模具型号管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult removeAll(String ids){
        String[] idss = ids.split(",");
        int success = 0;
        try {
            success = imModelService.removeAll(idss);
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
