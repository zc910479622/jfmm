package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.Main;
import com.jinfei.jfmm.model.SelectByMould;
import com.jinfei.jfmm.model.mainTZ;
import com.jinfei.jfmm.model.prodCate;

import java.util.List;

public interface IMainService {

    List<Main> selectAll(Main main);

    int add(Main main);

    int remove(String[] ids);

    int edit(Main main);

    List<mainTZ> selectTZAll(mainTZ main);

    int addMainTZ(mainTZ main);

    int editMainTZ(mainTZ main);

    List<mainTZ> selectMatchNo(mainTZ mainTZ);

    List<mainTZ> selectByMould(SelectByMould selectByMould);

    List<prodCate> prodCateList();

    List<mainTZ> selectbMainList(String seat, String type, Integer pageSize, Integer pageNum);
    
    mainTZ getMainTZ(String id);

    boolean updateRack(String seat, String bMainId, String request_org);

    Integer getTotal(mainTZ main);

    List<mainTZ> getSeat();

    int getMatchTotal(String seat, String type);

    Integer getTotalByMould(SelectByMould select);

    int addMainTZs(List<mainTZ> mainTZS);

    List<mainTZ> selectTZByMouldId(String mouldID);

    void setBF(String part_id, String location);
}
