package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.mCost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface mCostMapper {
    Boolean deleteByPrimaryKey(@Param("fId") String fId, @Param("prodCate") String prodCate,@Param("castType") String s2);

    boolean insert(mCost record);

    int insertSelective(mCost record);

    mCost selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(mCost record);

    boolean updateByPrimaryKey(mCost record);

    List<mCost> list();

    boolean check(mCost mCost);

    mCost getCost(@Param("fId") String s, @Param("prodCate") String s1, @Param("castType")String s2);

    mCost getCostOf(@Param("modelId") String modelId, @Param("cast_type_id")String cast_type_id);
}