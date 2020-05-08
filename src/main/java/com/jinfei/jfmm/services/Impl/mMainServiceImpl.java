package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.mMainMapper;
import com.jinfei.jfmm.model.mMain;
import com.jinfei.jfmm.services.IMMainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class mMainServiceImpl implements IMMainService {
    @Resource
    private mMainMapper mMainMapper;

    @Override
    public List<mMain> list(mMain main) {
        return mMainMapper.list(main);
    }

    @Override
    public Object getMain(String fId) {
        return mMainMapper.selectByPrimaryKey(fId);
    }

    @Override
    public boolean insertMain(mMain main) {
        return mMainMapper.insertSelective(main);
    }

    @Override
    public boolean updateMain(mMain main) {
        return mMainMapper.updateByPrimaryKeySelective(main);
    }

    @Override
    public boolean delete(String fId) {
        return mMainMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public int removeAll(String[] idss) {
        return mMainMapper.removeAll(idss);
    }

    @Override
    public boolean checkFNo(String fId, String fNo) {
        return mMainMapper.checkFNo(fId,fNo);
    }

    @Override
    public mMain selectByPrimaryKey(String fId) {
        return mMainMapper.selectByPrimaryKey(fId);
    }

    @Override
    public Integer getTotal(mMain main) {
        return mMainMapper.getTotal(main);
    }

    @Override
    public Boolean setAmortization(String ids, String amortization) {
        return mMainMapper.setAmortization(ids,amortization);
    }

    @Override
    public List<mMain> getMainList(String id) {
        return mMainMapper.getMainList(id);
    }

    @Override
    public Boolean setAmortizationTime(String ids, String amortizationTime) {
        return mMainMapper.setAmortizationTime(ids,amortizationTime);
    }


}
