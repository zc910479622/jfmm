package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.aOrg;
import com.jinfei.jfmm.services.IAOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class aOrgServiceImpl implements IAOrgService {

    @Resource
    private com.jinfei.jfmm.dao.aOrgMapper aOrgMapper;

    @Override
    public List<aOrg> list(aOrg aOrg) {
        return aOrgMapper.list(aOrg);
    }

    @Override
    public boolean insertAOrg(aOrg aOrg) {
        return aOrgMapper.insertSelective(aOrg);
    }

    @Override
    public aOrg getAOrg(String fid) {
        return aOrgMapper.selectByPrimaryKey(fid);
    }

    @Override
    public Integer delete(String fId) {
        return aOrgMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public aOrg selectByPrimaryKey(String fId) {
        return aOrgMapper.selectByPrimaryKey(fId);
    }

    @Override
    public boolean updateAOrg(aOrg aOrg) {
        return aOrgMapper.updateByPrimaryKeySelective(aOrg);
    }

    @Override
    public List<aOrg> getWare() {
        return aOrgMapper.getWare();
    }

    @Override
    public List<aOrg> getWareB(String fId) {
        return aOrgMapper.getWareB(fId);
    }

    @Override
    public boolean checkFName(String fName, String fPid, String fId) {
        return aOrgMapper.checkFName(fName,fPid,fId);
    }

    @Override
    public List<aOrg> divisionList() {
        return aOrgMapper.divisionList();
    }

    @Override
    public List<aOrg> getWareTree(String fId) {
        return aOrgMapper.getWareTree(fId);
    }

}
