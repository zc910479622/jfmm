package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.aCust;

import java.util.List;

public interface IACustService {
    List<aCust> list(aCust aCust);

    Object getCust(String fId);

    boolean insertCust(aCust aCust);

    boolean updateCust(aCust aCust);

    boolean delete(String fId);

    boolean checkFName(String fName, String fId);
}
