package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.ccgcy;

import java.util.List;

public interface IccgcyService {
    String callfun(String start, String end);

    List<ccgcy> list();

    List<ccgcy> listDept();

    List<ccgcy> listTable();
}
