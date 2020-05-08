package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.mMain;

import java.util.List;

public interface IMMainService {
    List<mMain> list(mMain main);

    Object getMain(String fId);

    boolean insertMain(mMain main);

    boolean updateMain(mMain main);

    boolean delete(String fId);

    int removeAll(String[] idss);

    boolean checkFNo(String fId, String fNo);

    mMain selectByPrimaryKey(String fId);

    Integer getTotal(mMain main);

    Boolean setAmortization(String ids, String amortization);

    List<mMain> getMainList(String id);

    Boolean setAmortizationTime(String ids, String a);
}
