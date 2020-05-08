package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.IIMainDao;
import com.jinfei.jfmm.model.IMain;
import com.jinfei.jfmm.services.IIMainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IIMainSerciceImpl implements IIMainService {

    @Resource
    private IIMainDao iiMainDao;

    @Override
    public int add(IMain iMain) {
        return iiMainDao.add(iMain);
    }

    @Override
    public List<IMain> selectAll(IMain iMain) {
        return iiMainDao.selectAll(iMain);
    }

    @Override
    public int remove(String[] ids) {
        return iiMainDao.remove(ids);
    }

    @Override
    public int Receive(IMain iMain) {
        return iiMainDao.Receive(iMain);
    }

    @Override
    public String selectPath(String id) {
        return iiMainDao.selectPath(id);
    }
}
