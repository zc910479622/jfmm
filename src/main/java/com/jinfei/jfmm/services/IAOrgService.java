package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.aOrg;

import java.util.List;

public interface IAOrgService {
    List<aOrg> list(aOrg aOrg);

    boolean insertAOrg(aOrg aOrg);

    aOrg getAOrg(String fid);

    Integer delete(String fId);

    aOrg selectByPrimaryKey(String fId);

    boolean updateAOrg(aOrg aOrg);

    List<aOrg> getWare();

    List<aOrg> getWareB(String ware);

    boolean checkFName(String fName, String fPid, String fId);

    List<aOrg>  divisionList();

    List<aOrg> getWareTree(String fId);
}
