package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.model.aDict;
import com.jinfei.jfmm.services.IADictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Basic/Dict")
public class aDictController extends BaseException {

    private String prefix = "/Basic/Dict";

    @Resource
    private IADictService iaDictService;

    @RequiresPermissions("Basic:Dict:view")
    @RequestMapping("")
    public String dict(){
        return prefix+"/dict";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<aDict> list(aDict aDict){
        return iaDictService.list(aDict);
    }

    //跳转新增页面
    @RequestMapping(value = {"/add/{fId}"},method = RequestMethod.GET)
    public String add(@PathVariable("fId") String fId, ModelMap modelMap){
        modelMap.addAttribute("aDict",iaDictService.getDict(fId));
        return "Basic/Dict/add";
    }

    //新增保存
    @RequiresPermissions("Basic:Dict:add")
    @Log(title = "字典管理", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/add"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(aDict aDict){
        aDict.setEditUser("admin");
        System.out.println(aDict.toString());
        try {
            if (iaDictService.insertDict(aDict)){
                return AjaxResult.success();
            }else {
                return AjaxResult.error();
            }
        }catch (Exception e){
            return AjaxResult.error();
        }

    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"},method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, HttpServletRequest request){
        request.setAttribute("aDict",iaDictService.getDict(fid));
        return "Basic/Dict/edit";
    }

    @RequiresPermissions("Basic:Dict:edit")
    //修改保存
    @Log(title = "字典管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/edit"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editSave(aDict aDict){

        if (iaDictService.updateDict(aDict)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequiresPermissions("Basic:Dict:remove")
    @Log(title = "字典管理", businessType = BusinessType.DELETE)
    @RequestMapping(value = {"/remove/{fId}"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(@PathVariable("fId") String fId){
        if (iaDictService.delete(fId)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = "checkFValue",method = RequestMethod.POST)
    @ResponseBody
    public String checkFValue(@RequestParam String fValue, String fPid,String fId) throws IOException {
        String jsonResult = "";

        if (fValue != null) {

            boolean b = this.iaDictService.checkFValue(fValue, fPid, fId);
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

    @RequestMapping(value = "checkFLable",method = RequestMethod.POST)
    @ResponseBody
    public String checkFLable(@RequestParam String fLable, String fPid,String fId) throws IOException {
        String jsonResult = "";

        if (fLable != null) {

            boolean b = this.iaDictService.checkFLable(fLable, fPid,fId);
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
