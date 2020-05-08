package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.IDetl;

import java.util.List;

public interface IIDetlService {
    int add(List<IDetl> detls);

    List<IDetl> selectAll(IDetl iDetl);

    int remove(String[] ids);

    boolean removeByMainId(String[] ids);

    int saveIMG(List<IDetl> detls);

    void receTime(IDetl iDetl);

    void path(String f_id, String img_path);
}
