package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.mModel;

import java.util.List;

public interface IMModelService {
    List<mModel> list(mModel mModel);

    boolean delete(String fId);

    boolean updateModel(mModel model);

    Object getModel(String fid);

    boolean insertModel(mModel model);

    int removeAll(String[] idss);

    mModel selectByPrimaryKey(String fId);
}
