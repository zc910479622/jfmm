package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.IMainDao;
import com.jinfei.jfmm.model.Main;
import com.jinfei.jfmm.model.SelectByMould;
import com.jinfei.jfmm.model.mainTZ;
import com.jinfei.jfmm.model.prodCate;
import com.jinfei.jfmm.services.IMainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MainServiceImpl implements IMainService {

    @Resource
    private IMainDao mainDao;

    @Override
    public List<Main> selectAll(Main main) {
        return mainDao.selectAll(main);
    }

    @Override
    public int add(Main main) {
        return mainDao.add(main);
    }

    @Override
    public int remove(String[] ids) {
        return mainDao.remove(ids);
    }

    @Override
    public int edit(Main main) {
        return mainDao.edit(main);
    }

    @Override
    public List<mainTZ> selectTZAll(mainTZ main) {
        return mainDao.selectTZAll(main);
    }

    @Override
    public int addMainTZ(mainTZ main) {
        return mainDao.addMainTZ(main);
    }

    @Override
    public int editMainTZ(mainTZ main) {
        return mainDao.editMainTZ(main);
    }

    @Override
    public List<mainTZ> selectMatchNo(mainTZ mainTZ) {
        return mainDao.selectMatchNo(mainTZ);
    }

    @Override
    public List<mainTZ> selectByMould(SelectByMould selectByMould) {
        return mainDao.selectByMould(selectByMould);
    }

    @Override
    public List<prodCate> prodCateList() {
        return mainDao.prodCateList();
    }

    @Override
    public List<mainTZ> selectbMainList(String seat, String type, Integer pageSize, Integer pageNum) {
        return mainDao.selectbMainList(seat,type,pageSize,pageNum);
    }

    @Override
    public mainTZ getMainTZ(String id) {
        return mainDao.getMainTZ(id);
    }

    @Override
    public boolean updateRack(String seat, String bMainId, String request_org) {
        return mainDao.updateRack(seat,bMainId,request_org);
    }

    @Override
    public Integer getTotal(mainTZ main) {
        return mainDao.getTotal(main);
    }

    @Override
    public List<mainTZ> getSeat() {
        return mainDao.getSeat();
    }

    @Override
    public int getMatchTotal(String seat, String type) {
        return mainDao.getMatchTotal(seat,type);
    }

    @Override
    public Integer getTotalByMould(SelectByMould select) {
        return mainDao.getTotalByMould(select);
    }

    @Override
    public int addMainTZs(List<mainTZ> mainTZS) {
        return mainDao.addMainTZs(mainTZS);
    }

    @Override
    public List<mainTZ> selectTZByMouldId(String mouldID) {
        return mainDao.selectTZByMouldId(mouldID);
    }

    @Override
    public void setBF(String part_id, String location) {
        mainDao.setBF(part_id,location);
    }
}
