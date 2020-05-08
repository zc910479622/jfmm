package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.kSeat;
import com.jinfei.jfmm.services.IKSeatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class kSeatServiceImpl implements IKSeatService {
    @Resource
    private com.jinfei.jfmm.dao.kSeatMapper kSeatMapper;

    @Override
    public List<kSeat> list() {
        return kSeatMapper.list();
    }

    @Override
    public Object getKSeat(String s) {
        return kSeatMapper.selectByPrimaryKey(s);
    }

    @Override
    public boolean addSave(kSeat kSeat) {
        return kSeatMapper.insertSelective(kSeat);
    }

    @Override
    public boolean editSave(kSeat kSeat) {
        return kSeatMapper.updateByPrimaryKeySelective(kSeat);
    }

    @Override
    public boolean remove(String s) {
        return kSeatMapper.deleteByPrimaryKey(s);
    }

    @Override
    public List<kSeat> getOption(String fPid, String state) {
        return kSeatMapper.getOption(fPid,state);
    }

    @Override
    public List<kSeat> list(String seat) {
        return kSeatMapper.getSeat(seat);
    }

    @Override
    public List<kSeat> getSeatWare(String seat) {
        return kSeatMapper.getSeatWare(seat);
    }

    @Override
    public List<kSeat> getChildren(String fPid, String seat) {
        return kSeatMapper.getChildren(fPid,seat);
    }

    @Override
    public void editIsStatus(String fId) {
        kSeatMapper.editIsStatus(fId);
    }

    @Override
    public void editIsStatusAll(String fId) {
        kSeatMapper.editIsStatusAll(fId);
    }
}
