package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.aMenu;

import java.util.List;

public interface aMenuMapper {
    boolean deleteByPrimaryKey(String menuId);

    boolean insert(aMenu record);

    boolean insertSelective(aMenu record);

    aMenu selectByPrimaryKey(String menuId);

    boolean updateByPrimaryKeySelective(aMenu record);

    boolean updateByPrimaryKey(aMenu record);

    List<aMenu> list(aMenu aMenu);

    Object selectMenuById(String menuId);

    List<aMenu> selectMenuAll();

    boolean checkMenuNameUnique(aMenu menu);

    List<aMenu> selectMenusByUser(String userId);
}