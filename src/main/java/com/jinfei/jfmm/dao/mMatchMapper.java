package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.mMatch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface mMatchMapper {
    boolean deleteByPrimaryKey(String fId);

    int insert(mMatch record);

    boolean insertSelective(mMatch record);

    mMatch selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(mMatch record);

    int updateByPrimaryKey(mMatch record);

    List<mMatch> matchList(mMatch mMatch);

    Object getbModelId(@Param("type") String s, @Param("fId") String s1);

    boolean checkFid(@Param("fId") String fId);

    int getTotal(mMatch mMatch);

    boolean delete(String mMainId);
}