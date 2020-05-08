package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.Role;
import com.jinfei.jfmm.model.aMenu;

import java.util.List;
import java.util.Map;

public interface IAMenuService {
    List<aMenu> list(aMenu aMenu);

    aMenu getMenu(String menuId);

    boolean insertMenu(aMenu aMenu);

    boolean updateMenu(aMenu aMenu);

    boolean delete(String menuId);

    Object selectMenuById(String menuId);

    List<Map<String, Object>> menuTreeData();

    boolean checkMenuNameUnique(aMenu menu);

    List<Map<String, Object>> roleMenuTreeData(Role role);

    List<aMenu> selectMenusByUser(String userId);
}
