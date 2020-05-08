package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.mMatch;
import com.jinfei.jfmm.services.IMMatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class mMatchServiceImpl implements IMMatchService {

    @Resource
    private com.jinfei.jfmm.dao.mMatchMapper mMatchMapper;


    @Override
    public List<mMatch> matchList(mMatch mMatch) {
        return mMatchMapper.matchList(mMatch);
    }

    @Override
    public mMatch getbModelId(String fId) {
        return mMatchMapper.selectByPrimaryKey(fId);
    }

    @Override
    public boolean checkFid(String fId) {
        return mMatchMapper.checkFid(fId);
    }

    @Override
    public boolean insert(mMatch mMatch) {
        return mMatchMapper.insertSelective(mMatch);
    }

    @Override
    public boolean update(mMatch mMatch) {
        return mMatchMapper.updateByPrimaryKeySelective(mMatch);
    }

    @Override
    public boolean remove(mMatch mMatch) {
        return mMatchMapper.deleteByPrimaryKey(mMatch.getfId());
    }

    @Override
    public boolean delete(String mMainId) {
        return mMatchMapper.delete(mMainId);
    }

    @Override
    public int getTotal(mMatch mMatch) {
        return mMatchMapper.getTotal(mMatch);
    }

}
