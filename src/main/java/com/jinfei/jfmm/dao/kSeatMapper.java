package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.kSeat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface kSeatMapper {
    boolean deleteByPrimaryKey(String fId);

    boolean insertSelective(kSeat record);

    kSeat selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(kSeat record);

    List<kSeat> list();

    List<kSeat> getOption(@Param("fPid") String fPid, @Param("state") String state);

    List<kSeat> getSeatWare(@Param("fId") String seat);

    List<kSeat> getSeat(@Param("fId") String seat);

    List<kSeat> getChildren(@Param("fPid") String fPid, @Param("fId") String seat);

    void editIsStatus(String fId);

    void editIsStatusAll(String fId);
}