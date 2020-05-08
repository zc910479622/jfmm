package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.model.aOrg;
import com.jinfei.jfmm.services.IAOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Basic/Dept")
public class aOrgController extends BaseException {

    private String prefix = "/Basic/Dept";

    @Resource
    private IAOrgService iaOrgService;

    @RequiresPermissions("Basic:Dept:view")
    @RequestMapping("")
    public String dept(){
        return prefix+"/dept";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<aOrg> list(HttpServletRequest request) {
        aOrg aOrg = new aOrg();
        aOrg.setfName(request.getParameter("fName") );
        aOrg.setfPid(request.getParameter("fPid"));
        aOrg.setIsWh(Byte.valueOf("2"));
        if (StringUtils.isNotEmpty(request.getParameter("isWh"))) {
            aOrg.setIsWh(Byte.valueOf(request.getParameter("isWh")));
        }
        return iaOrgService.list(aOrg);
    }

//    @RequestMapping(value = {"/remove/{fId}"})
//    @ResponseBody
//    public AjaxResult remove(@PathVariable("fId") String fId){
//        return
//    }

    //跳转新增页面
    @RequestMapping(value = {"/add/{fId}"}, method = RequestMethod.GET)
    public String add(@PathVariable("fId") String fId, ModelMap modelMap) {
        modelMap.addAttribute("aOrg", iaOrgService.getAOrg(fId));
        return "Basic/Dept/add";
    }

    //新增保存
    @RequiresPermissions("Basic:Dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(aOrg aOrg) {
        System.out.println(aOrg.toString());
        try {
            if (iaOrgService.insertAOrg(aOrg)) {
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
        request.setAttribute("aOrg", iaOrgService.getAOrg(fid));
        return "Basic/Dept/edit";
    }

    //修改保存
    @RequiresPermissions("Basic:Dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editSave(aOrg aOrg) {
        if (iaOrgService.updateAOrg(aOrg)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequiresPermissions("Basic:Dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequestMapping(value = {"/remove/{fId}"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(@PathVariable("fId") String fId) {
        if (iaOrgService.delete(fId) > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 选择部门树
     */
    @RequestMapping("/selectDeptTree/{fPid}")
    public String selectDeptTree(@PathVariable("fPid") String fPid, ModelMap mmap) {
        if ("1".equals(fPid)){
            mmap.put("aOrg", new aOrg());
        }else {
            mmap.put("aOrg", iaOrgService.getAOrg(fPid));
        }
        return "Basic/Dept/tree";
    }

    //获取组织机构
    @RequestMapping(value = "treeOrg")
    @ResponseBody
    public List<Map<String, Object>> treeOrg(String fId) {
        List<aOrg> aOrgs = iaOrgService.getWareTree("");
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        trees = getTrees(aOrgs, false, null);
        return trees;
    }

    @RequestMapping("/checkFName")
    @ResponseBody
    public String checkFName(@RequestParam String fName, String fPid,String fId) throws IOException {
        String jsonResult = "";

        if (fName != null) {
            boolean b = this.iaOrgService.checkFName(fName, fPid,fId);
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

    /**
     * 对象转部门树
     *
     * @param aOrgList     部门列表
     * @param isCheck      是否需要选中
     * @param roleDeptList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<aOrg> aOrgList, boolean isCheck, List<String> roleDeptList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (com.jinfei.jfmm.model.aOrg aOrg: aOrgList) {
            if (1==aOrg.getfUsable()) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("id", aOrg.getfId());
                deptMap.put("pId", aOrg.getfPid());
                deptMap.put("name", aOrg.getfName());
                deptMap.put("title", aOrg.getfName());
                if (isCheck) {
                    deptMap.put("checked", roleDeptList.contains(aOrg.getfId() + aOrg.getfName()));
                } else {
                    deptMap.put("checked", false);
                }
                trees.add(deptMap);
            }
        }
        return trees;
    }

}
