package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.aDict;
import com.jinfei.jfmm.services.IADictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class aDictServiceImpl implements IADictService {

    @Resource
    private com.jinfei.jfmm.dao.aDictMapper aDictMapper;

    @Override
    public List<aDict> list(aDict aDict) {
        return aDictMapper.list(aDict);
    }

    @Override
    public aDict getDict(String fId) {
        return aDictMapper.selectByPrimaryKey(fId);
    }

    @Override
    public boolean insertDict(aDict aDict) {
        return aDictMapper.insertSelective(aDict);
    }

    @Override
    public boolean updateDict(aDict aDict) {
        return aDictMapper.updateByPrimaryKeySelective(aDict);
    }

    @Override
    public boolean delete(String fId) {
        return aDictMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public List<aDict> getDictType() {
        return aDictMapper.getDictType();
    }

    @Override
    public boolean checkFValue(String fValue, String fPid, String fId) {
        return aDictMapper.checkFValue(fValue,fPid,fId);
    }

    @Override
    public boolean checkFLable(String fLable, String fPid, String fId) {
        return aDictMapper.checkFLable(fLable,fPid,fId);
    }

    @Override
    public List<aDict> getDictOption(String fValue) {
        return aDictMapper.getDictOption(fValue);
    }
}
