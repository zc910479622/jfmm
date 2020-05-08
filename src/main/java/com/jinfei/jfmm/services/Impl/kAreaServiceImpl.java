package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.kArea;
import com.jinfei.jfmm.services.IKAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class kAreaServiceImpl implements IKAreaService {
    @Resource
    private com.jinfei.jfmm.dao.kAreaMapper kAreaMapper;

    @Override
    public List<kArea> list() {
        return kAreaMapper.list();
    }

    @Override
    public Object getKArea(String s) {
        return kAreaMapper.selectByPrimaryKey(s);
    }

    @Override
    public boolean addSave(kArea kArea) {
        return kAreaMapper.insertSelective(kArea);
    }

    @Override
    public boolean editSave(kArea kArea) {
        return kAreaMapper.updateByPrimaryKeySelective(kArea);
    }

    @Override
    public boolean remove(String s) {
        return kAreaMapper.deleteByPrimaryKey(s);
    }

    @Override
    public List<kArea> getOption(String fPid) {
        return kAreaMapper.getOption(fPid);
    }

    @Override
    public List<kArea> list(String area) {
        return kAreaMapper.getArea(area);
    }

    @Override
    public List<kArea> getChildren(String fPid, String area) {
        return kAreaMapper.getChildren(fPid,area);
    }
}
