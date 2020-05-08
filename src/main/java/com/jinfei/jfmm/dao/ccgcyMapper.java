package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.ccgcy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ccgcyMapper {
    int insert(ccgcy record);

    int insertSelective(ccgcy record);

    String callfun(@Param("start") String start, @Param("end") String end);

    List<ccgcy> list();

    List<ccgcy> listDept();

    List<ccgcy> listTable();
}