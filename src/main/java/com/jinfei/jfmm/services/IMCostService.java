package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.mCost;

import java.util.List;

public interface IMCostService {
    List<mCost> list();

    boolean add(mCost mCost);

    boolean check(mCost mCost);

    mCost getCost(String s, String s1, String s2);

    boolean edit(mCost mCost);

    Boolean remove(String s, String s1, String s2);

    mCost getCostOf(String modelId, String cast_type_id);
}
