package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.mModel;
import com.jinfei.jfmm.services.IMModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class mModelServiceImpl implements IMModelService {
    @Resource
    private com.jinfei.jfmm.dao.mModelMapper mModelMapper;

    @Override
    public List<mModel> list(mModel mModel) {
        return mModelMapper.list(mModel);
    }

    @Override
    public boolean delete(String fId) {
        return mModelMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public boolean updateModel(mModel model) {
        return mModelMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Object getModel(String fid) {
        return mModelMapper.selectByPrimaryKey(fid);
    }

    @Override
    public boolean insertModel(mModel model) {
        return mModelMapper.insertSelective(model);
    }

    @Override
    public int removeAll(String[] idss) {
        return mModelMapper.removeAll(idss);
    }

    @Override
    public mModel selectByPrimaryKey(String fId) {
        return mModelMapper.selectByPrimaryKey(fId);
    }
}
