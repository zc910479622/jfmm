package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.aCust;
import com.jinfei.jfmm.services.IACustService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class aCustServiceImpl implements IACustService {

    @Resource
    private com.jinfei.jfmm.dao.aCustMapper aCustMapper;

    @Override
    public List<aCust> list(aCust aCust) {
        return aCustMapper.list(aCust);
    }

    @Override
    public Object getCust(String fId) {
        return aCustMapper.selectByPrimaryKey(fId);
    }

    @Override
    public boolean insertCust(aCust aCust) {
        return aCustMapper.insertSelective(aCust);
    }

    @Override
    public boolean updateCust(aCust aCust) {
        return aCustMapper.updateByPrimaryKeySelective(aCust);
    }

    @Override
    public boolean delete(String fId) {
        return aCustMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public boolean checkFName(String fName, String fId) {
        return aCustMapper.checkFName(fName,fId);
    }
}
