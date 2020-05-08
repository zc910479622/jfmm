package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.sRecord;

import java.util.List;

public interface sRecordMapper {
    int deleteByPrimaryKey(String fId);

    int insert(sRecord record);

    int insertSelective(sRecord record);

    sRecord selectByPrimaryKey(String fId);

    int updateByPrimaryKeySelective(sRecord record);

    int updateByPrimaryKey(sRecord record);

    List<sRecord> list();
}