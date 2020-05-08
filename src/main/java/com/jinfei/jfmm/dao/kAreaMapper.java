package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.kArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface kAreaMapper {
    boolean deleteByPrimaryKey(String fId);

    int insert(kArea record);

    boolean insertSelective(kArea record);

    kArea selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(kArea record);

    int updateByPrimaryKey(kArea record);

    List<kArea> list();

    List<kArea> getOption(String fPid);

    List<kArea> getArea(@Param("fId") String area);

    List<kArea> getChildren(@Param("fPid") String fPid, @Param("fId") String area);
}