package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.kShelf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface kShelfMapper {
    boolean deleteByPrimaryKey(String fId);

    int insert(kShelf record);

    boolean insertSelective(kShelf record);

    kShelf selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(kShelf record);

    int updateByPrimaryKey(kShelf record);

    List<kShelf> list();

    List<kShelf> getOption(String fPid);

    List<kShelf> getShelf(@Param("fId") String shelf);

    List<kShelf> getChildren(@Param("fPid") String fPid, @Param("fId") String shelf);

    void editIsStatus(String fId);
}