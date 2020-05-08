package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.aDict;

import java.util.List;

public interface IADictService {
    List<aDict> list(aDict aDict);

    aDict getDict(String fId);

    boolean insertDict(aDict aDict);

    boolean updateDict(aDict aDict);

    boolean delete(String fId);

    List<aDict> getDictType();

    boolean checkFValue(String fValue, String fPid, String fId);

    boolean checkFLable(String fLable, String fPid, String fId);

    List<aDict> getDictOption(String fValue);
}
