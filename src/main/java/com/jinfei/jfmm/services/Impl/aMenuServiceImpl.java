package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.dao.aMenuMapper;
import com.jinfei.jfmm.model.Role;
import com.jinfei.jfmm.model.aMenu;
import com.jinfei.jfmm.services.IAMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class aMenuServiceImpl implements IAMenuService {
    @Resource
    private aMenuMapper aMenuMapper;



    @Override
    public List<aMenu> list(aMenu aMenu) {
        return aMenuMapper.list(aMenu);
    }

    @Override
    public aMenu getMenu(String menuId) {
        return aMenuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public boolean insertMenu(aMenu aMenu) {
        return aMenuMapper.insertSelective(aMenu);
    }

    @Override
    public boolean updateMenu(aMenu aMenu) {
        return aMenuMapper.updateByPrimaryKey(aMenu);
    }

    @Override
    public boolean delete(String menuId) {
        return aMenuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public Object selectMenuById(String menuId) {
        return aMenuMapper.selectMenuById(menuId);
    }

    @Override
    public List<Map<String, Object>> menuTreeData() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<aMenu> menuList = aMenuMapper.list(new aMenu());
        trees = getTrees(menuList, false, null, false);
        return trees;
    }

    @Override
    public boolean checkMenuNameUnique(aMenu menu) {
        return aMenuMapper.checkMenuNameUnique(menu);
    }

    @Override
    public List<Map<String, Object>> roleMenuTreeData(Role role) {
        String menuId = role.getMenuId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<aMenu> menuList = aMenuMapper.list(new aMenu());
        if (StringUtils.isNotNull(menuId))
        {
            List<String> menuIds = new ArrayList<>(Arrays.asList(menuId.split(",")));
            trees = getTrees(menuList, true, menuIds, true);
        }
        else
        {
            trees = getTrees(menuList, false, null, true);
        }
        return trees;
    }

    @Override
    public List<aMenu> selectMenusByUser(String userId) {
        if ("0".equals(userId)){
            return getChildPerms(aMenuMapper.selectMenuAll(),"0");
        }
        return getChildPerms(aMenuMapper.selectMenusByUser(userId),"0");
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param isCheck 是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return
     */
    public List<Map<String, Object>> getTrees(List<aMenu> menuList, boolean isCheck, List<String> roleMenuList,
                                              boolean permsFlag)
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (aMenu menu : menuList)
        {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.getMenuId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name", transMenuName(menu, roleMenuList, permsFlag));
            deptMap.put("title", menu.getMenuName());
            if (isCheck)
            {
                deptMap.put("checked", roleMenuList.contains(menu.getMenuId()));
            }
            else
            {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    public String transMenuName(aMenu menu, List<String> roleMenuList, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }


    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<aMenu> getChildPerms(List<aMenu> list, String parentId)
    {
        List<aMenu> returnList = new ArrayList<aMenu>();
        for (aMenu a:list)
        {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (parentId.equals(a.getParentId())) {
                recursionFn(list, a);
                returnList.add(a);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<aMenu> list, aMenu t)
    {
        // 得到子节点列表
        List<aMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (aMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<aMenu> it = childList.iterator();
                while (it.hasNext())
                {
                    aMenu n = (aMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<aMenu> getChildList(List<aMenu> list, aMenu t)
    {
        List<aMenu> tlist = new ArrayList<aMenu>();
        Iterator<aMenu> it = list.iterator();
        while (it.hasNext())
        {
            aMenu n = (aMenu) it.next();
            if (n.getParentId().equals(t.getMenuId()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<aMenu> list, aMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
