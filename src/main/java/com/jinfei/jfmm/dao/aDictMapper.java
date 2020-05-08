package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.aDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface aDictMapper {
    boolean deleteByPrimaryKey(String fId);

    boolean insert(aDict record);

    boolean insertSelective(aDict record);

    aDict selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(aDict record);

    boolean updateByPrimaryKey(aDict record);

    List<aDict> list(aDict aDict);

    List<aDict> getDictType();

    boolean checkFValue(@Param("fValue") String fValue, @Param("fPid") String fPid, @Param("fId") String fId);

    boolean checkFLable(@Param("fLable") String fLable, @Param("fPid") String fPid, @Param("fId") String fId);

    List<aDict> getDictOption(@Param("fValue") String fValue);
}