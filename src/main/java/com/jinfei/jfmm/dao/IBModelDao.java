package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.BModel;

import java.util.List;

public interface IBModelDao {
    List<BModel> selectAll(BModel bModel);

    int add(BModel bModel);

    int edit(BModel bModel);

    int remove(String[] idss);

    int addByMMain(List<BModel> bModels);

    Integer getTotal(BModel bModel);

    BModel getBModel(String modelId);
}
