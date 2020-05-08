package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.aSupp;

import java.util.List;

public interface IASuppService {
    List<aSupp> list(aSupp aSupp);

    Object getSupp(String fId);

    boolean insertSupp(aSupp aSupp);

    boolean updateSupp(aSupp aSupp);

    boolean delete(String fId);

    boolean checkFName(String fName, String fId);
}
