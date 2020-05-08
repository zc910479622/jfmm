package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.Main;
import com.jinfei.jfmm.model.SelectByMould;
import com.jinfei.jfmm.model.mainTZ;
import com.jinfei.jfmm.model.prodCate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMainDao {
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

    List<mainTZ> selectbMainList(@Param("seat") String seat, @Param("type") String type, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

    mainTZ getMainTZ(String id);

    boolean updateRack(@Param("seat") String seat, @Param("bMainId") String bMainId,@Param("request_org") String request_org);

    Integer getTotal(mainTZ main);

    List<mainTZ> getSeat();

    int getMatchTotal(@Param("seat") String seat, @Param("type") String type);

    Integer getTotalByMould(SelectByMould select);

    int addMainTZs(List<mainTZ> mainTZS);

    List<mainTZ> selectTZByMouldId(@Param("mouldID") String mouldID);

    void setBF(@Param("part_id") String part_id, @Param("location") String location);
}
