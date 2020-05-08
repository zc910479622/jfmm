package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.mCost;
import com.jinfei.jfmm.services.IMCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class mCostServiceImpl implements IMCostService {

    @Autowired(required = false)
    private com.jinfei.jfmm.dao.mCostMapper mCostMapper;


    @Override
    public List<mCost> list() {
        return mCostMapper.list();
    }

    @Override
    public boolean add(mCost mCost) {
        return mCostMapper.insert(mCost);
    }

    @Override
    public boolean check(mCost mCost) {
        return mCostMapper.check(mCost);
    }

    @Override
    public mCost getCost(String s, String s1, String s2) {
        return mCostMapper.getCost(s,s1,s2);
    }

    @Override
    public boolean edit(mCost mCost) {
        return mCostMapper.updateByPrimaryKeySelective(mCost);
    }

    @Override
    public Boolean remove(String s, String s1, String s2) {
        return mCostMapper.deleteByPrimaryKey(s,s1,s2);
    }

    @Override
    public mCost getCostOf(String modelId, String cast_type_id) {
        return mCostMapper.getCostOf(modelId,cast_type_id);
    }
}
