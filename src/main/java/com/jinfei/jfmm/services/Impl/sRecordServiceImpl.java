package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.sRecordMapper;
import com.jinfei.jfmm.model.sRecord;
import com.jinfei.jfmm.services.ISRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class sRecordServiceImpl implements ISRecordService {
    @Resource
    private  sRecordMapper recordMapper;


    @Override
    public List<sRecord> list() {
        return recordMapper.list();
    }

    @Override
    public void insert(sRecord sRecord) {
        recordMapper.insert(sRecord);
    }
}
