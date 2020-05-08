package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.mMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface mMainMapper {
    boolean deleteByPrimaryKey(String fId);

    int insert(mMain record);

    boolean insertSelective(mMain record);

    mMain selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(mMain record);

    int updateByPrimaryKey(mMain record);

    int removeAll(String[] idss);

    List<mMain> list(mMain main);

    boolean checkFNo(@Param("fId") String fId, @Param("fNo") String fNo);

    Integer getTotal(mMain main);

    Boolean setAmortization(@Param("fNo") String fNo, @Param("amortization") String amortization);

    List<mMain> getMainList(String id);

    Boolean setAmortizationTime(@Param("fNo")String ids, @Param("amortizationTime")String amortizationTime);
}