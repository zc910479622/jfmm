package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.model.kShelf;
import com.jinfei.jfmm.services.IKShelfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class kShelfServiceImpl implements IKShelfService {

    @Resource
    private com.jinfei.jfmm.dao.kShelfMapper kShelfMapper;

    @Override
    public List<kShelf> list() {
        return kShelfMapper.list();
    }

    @Override
    public Object getKShelf(String s) {
        return kShelfMapper.selectByPrimaryKey(s);
    }

    @Override
    public boolean addSave(kShelf kShelf) {
        return kShelfMapper.insertSelective(kShelf);
    }

    @Override
    public boolean editSave(kShelf kShelf) {
        return kShelfMapper.updateByPrimaryKeySelective(kShelf);
    }

    @Override
    public boolean remove(String s) {
        return kShelfMapper.deleteByPrimaryKey(s);
    }

    @Override
    public List<kShelf> getOption(String fPid) {
        return kShelfMapper.getOption(fPid);
    }

    @Override
    public List<kShelf> list(String shelf) {
        return kShelfMapper.getShelf(shelf);
    }

    @Override
    public List<kShelf> getChildren(String fPid, String shelf) {
        return kShelfMapper.getChildren(fPid,shelf);
    }

    @Override
    public void editIsStatus(String fId) {
        kShelfMapper.editIsStatus(fId);
    }
}
