package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.model.aMenu;
import com.jinfei.jfmm.services.IAMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private IAMenuService menuService;

    @RequestMapping("index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = shiroUtil.getSysUser();        // 根据用户id取出菜单
        List<aMenu> menus = menuService.selectMenusByUser(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        return "index";
    }

    @RequestMapping("index.html")
    public String index1(ModelMap mmap)
    {
        // 取身份信息
        User user = shiroUtil.getSysUser();        // 根据用户id取出菜单
        List<aMenu> menus = menuService.selectMenusByUser(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        return "index";
    }

    @RequestMapping("/")
    public String index2(ModelMap mmap)
    {
        // 取身份信息
        User user = shiroUtil.getSysUser();        // 根据用户id取出菜单
        List<aMenu> menus = menuService.selectMenusByUser(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        return "index";
    }
}
