package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.IIDetlDao;
import com.jinfei.jfmm.model.IDetl;
import com.jinfei.jfmm.services.IIDetlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IDetlServiceImpl implements IIDetlService {

    @Resource
    private IIDetlDao iiDetlDao;

    @Override
    public int add(List<IDetl> detls) {
        return iiDetlDao.add(detls);
    }

    @Override
    public List<IDetl> selectAll(IDetl iDetl) {
        return iiDetlDao.selectAll(iDetl);
    }

    @Override
    public int remove(String[] ids) {
        return iiDetlDao.remove(ids);
    }

    @Override
    public boolean removeByMainId(String[] ids) {
        return iiDetlDao.removeByMainId(ids);
    }

    @Override
    public int saveIMG(List<IDetl> detls) {
        return iiDetlDao.saveIMG(detls);
    }

    @Override
    public void receTime(IDetl iDetl) {
        iiDetlDao.receTime(iDetl);
    }

    @Override
    public void path(String f_id, String img_path) {
        iiDetlDao.path(f_id,img_path);
    }
}
