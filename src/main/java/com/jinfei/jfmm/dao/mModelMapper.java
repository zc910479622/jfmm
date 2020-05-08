package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.mModel;

import java.util.List;

public interface mModelMapper {
    boolean deleteByPrimaryKey(String fId);

    boolean insert(mModel record);

    boolean insertSelective(mModel record);

    mModel selectByPrimaryKey(String fId);

    boolean updateByPrimaryKeySelective(mModel record);

    boolean updateByPrimaryKey(mModel record);

    List<mModel> list(mModel mModel);

    int removeAll(String[] idss);
}