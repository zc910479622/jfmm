package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.ccgcyMapper;
import com.jinfei.jfmm.model.ccgcy;
import com.jinfei.jfmm.services.IccgcyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ccgcyServiceImpl implements IccgcyService {

    @Resource
    private ccgcyMapper ccgcyMapper;

    @Override
    public String callfun(String start, String end) {
        return ccgcyMapper.callfun(start,end);
    }

    @Override
    public List<ccgcy> list() {
        return ccgcyMapper.list();
    }

    @Override
    public List<ccgcy> listDept() {
        return ccgcyMapper.listDept();
    }

    @Override
    public List<ccgcy> listTable() {
        return ccgcyMapper.listTable();
    }
}
