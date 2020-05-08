package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.aCust;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface aCustMapper {
    boolean deleteByPrimaryKey(String fId);

    int insert(aCust record);

    boolean insertSelective(aCust record);

    aCust selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(aCust record);

    int updateByPrimaryKey(aCust record);

    List<aCust> list(aCust aCust);

    boolean checkFName(@Param("fName") String fName, @Param("fId") String fId);
}