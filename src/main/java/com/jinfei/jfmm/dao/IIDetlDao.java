package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.IDetl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IIDetlDao {
    int add(List<IDetl> detls);

    List<IDetl> selectAll(IDetl iDetl);

    int remove(String[] ids);

    boolean removeByMainId(String[] ids);

    int saveIMG(List<IDetl> detls);

    void receTime(IDetl iDetl);

    void path(@Param("f_id") String f_id,@Param("img_path") String img_path);
}
