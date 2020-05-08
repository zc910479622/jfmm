package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.Role;
import com.jinfei.jfmm.model.aMenu;
import com.jinfei.jfmm.services.IAMenuService;
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
import java.util.Map;

@Controller
@RequestMapping("/Basic/Menu")
public class aMenuController extends BaseException {

    private String prefix = "/Basic/Menu";

    @Resource
    private IAMenuService iaMenuService;

    @RequiresPermissions("Basic:Menu:view")
    @RequestMapping("")
    public String menu(){
        return prefix+"/menu";
    }

    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    @ResponseBody
    public List<aMenu> list(aMenu aMenu ){
        return iaMenuService.list(aMenu);

    }

    //跳转新增页面
    @RequestMapping(value = {"/add/{menuId}"},method = RequestMethod.GET)
    public String add(@PathVariable("menuId") String menuId, ModelMap modelMap){
        modelMap.addAttribute("menu",iaMenuService.getMenu(menuId));
        return "Basic/Menu/add";
    }

    //新增保存
    @RequiresPermissions("Basic:Menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/add"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(aMenu aMenu){
        aMenu.setCreateBy(shiroUtil.getLoginName());
        if (StringUtils.isEmpty(aMenu.getUrl())) aMenu.setUrl("#");
        try {
            if (iaMenuService.insertMenu(aMenu)){
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
    public String edit(@PathVariable("id") String menuId, ModelMap modelMap){
        modelMap.addAttribute("menu",iaMenuService.getMenu(menuId));
        return "Basic/Menu/edit";
    }

    //修改保存
    @RequiresPermissions("Basic:Menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/edit"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editSave(aMenu aMenu){
        if (iaMenuService.updateMenu(aMenu)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequiresPermissions("Basic:Menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @RequestMapping(value = {"/remove/{menuId}"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") String menuId){
        if (iaMenuService.delete(menuId)){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    /**
     * 校验菜单名称
     */
    @RequestMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(aMenu menu)
    {
        String jsonResult = "";
        if (menu != null) {

            boolean b = iaMenuService.checkMenuNameUnique(menu);
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
     * 选择菜单树
     */
    @RequestMapping(value = "/selectMenuTree/{menuId}",method = RequestMethod.GET)
    public String selectMenuTree(@PathVariable("menuId") String menuId, ModelMap modelMap)
    {
        modelMap.put("menu", iaMenuService.selectMenuById(menuId));
        return "/Basic/Menu/tree";
    }

    /**
     * 加载所有菜单列表树
     */
    @RequestMapping("/menuTreeData")
    @ResponseBody
    public List<Map<String, Object>> menuTreeData()
    {
        List<Map<String, Object>> tree = iaMenuService.menuTreeData();
        return tree;
    }

    @RequestMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Map<String,Object>> roleMenuTreeData(Role role){
        return iaMenuService.roleMenuTreeData(role);
    }

}
