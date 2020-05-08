package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.mMatch;

import java.util.List;

public interface IMMatchService {


    List<mMatch> matchList(mMatch mMatch);

    mMatch getbModelId(String fId);

    boolean checkFid(String fId);

    boolean insert(mMatch mMatch);

    boolean update(mMatch mMatch);

    boolean remove(mMatch mMatch);

    boolean delete(String mMainId);

    int getTotal(mMatch mMatch);
}
