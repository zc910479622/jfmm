package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.sRecord;

import java.util.List;

public interface ISRecordService {
    List<sRecord> list();

    void insert(sRecord sRecord);
}
