package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.aOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface aOrgMapper {
    int deleteByPrimaryKey(String fId);

    boolean insert(aOrg record);

    boolean insertSelective(aOrg record);

    aOrg selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(aOrg record);

    int updateByPrimaryKey(aOrg record);

    List<aOrg> list(aOrg aOrg);

    List<aOrg> getWare();

    List<aOrg> getWareB(@Param("fId") String fId);

    boolean checkFName(@Param("fName") String fName, @Param("fPid") String fPid, @Param("fId") String fId);

    List<aOrg> divisionList();

    List<aOrg> getWareTree(String fId);
}