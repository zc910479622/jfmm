package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.aSupp;
import com.jinfei.jfmm.services.IASuppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class aSuppServiceImpl implements IASuppService {

    @Resource
    private com.jinfei.jfmm.dao.aSuppMapper aSuppMapper;

    @Override
    public List<aSupp> list(aSupp aSupp) {
        return aSuppMapper.list(aSupp);
    }

    @Override
    public Object getSupp(String fId) {
        return aSuppMapper.selectByPrimaryKey((fId));
    }

    @Override
    public boolean insertSupp(aSupp aSupp) {
        return aSuppMapper.insertSelective(aSupp);
    }

    @Override
    public boolean updateSupp(aSupp aSupp) {
        return aSuppMapper.updateByPrimaryKeySelective(aSupp);
    }

    @Override
    public boolean delete(String fId) {
        return aSuppMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public boolean checkFName(String fName, String fId) {
        return aSuppMapper.checkFName(fName,fId);
    }
}
