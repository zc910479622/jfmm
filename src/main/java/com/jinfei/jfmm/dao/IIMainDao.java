package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.IMain;

import java.util.List;

public interface IIMainDao {

    int add(IMain iMain);

    List<IMain> selectAll(IMain iMain);

    int remove(String[] ids);

    int Receive(IMain iMain);

    String selectPath(String id);
}
