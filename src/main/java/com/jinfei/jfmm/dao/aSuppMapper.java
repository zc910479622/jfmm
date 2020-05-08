package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.aSupp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface aSuppMapper {
    boolean deleteByPrimaryKey(String fId);

    int insert(aSupp record);

    boolean insertSelective(aSupp record);

    aSupp selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(aSupp record);

    int updateByPrimaryKey(aSupp record);

    List<aSupp> list(aSupp aSupp);

    boolean checkFName(@Param("fName") String fName, @Param("fId") String fId);
}