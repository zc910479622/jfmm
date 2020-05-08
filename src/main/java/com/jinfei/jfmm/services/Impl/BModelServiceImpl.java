package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.IBModelDao;
import com.jinfei.jfmm.model.BModel;
import com.jinfei.jfmm.services.IBModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BModelServiceImpl implements IBModelService {

    @Resource
    private IBModelDao ibModelDao;

    @Override
    public List<BModel> selectAll(BModel bModel) {
        return ibModelDao.selectAll(bModel);
    }

    @Override
    public int add(BModel bModel) {
        return ibModelDao.add(bModel);
    }

    @Override
    public int edit(BModel bModel) {
        return ibModelDao.edit(bModel);
    }

    @Override
    public int remove(String[] idss) {
        return ibModelDao.remove(idss);
    }

    @Override
    public int addByMMain(List<BModel> bModels) {
        return ibModelDao.addByMMain(bModels);
    }

    @Override
    public Integer getTotal(BModel bModel) {
        return ibModelDao.getTotal(bModel);
    }

    @Override
    public BModel getBModel(String modelId) {
        return ibModelDao.getBModel(modelId);
    }
}
