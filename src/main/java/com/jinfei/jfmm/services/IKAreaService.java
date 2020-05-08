package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.kArea;

import java.util.List;

public interface IKAreaService {
    List<kArea> list();

    Object getKArea(String s);

    boolean addSave(kArea kArea);

    boolean editSave(kArea kArea);

    boolean remove(String s);

    List<kArea> getOption(String fPid);

    List<kArea> list(String area);

    List<kArea> getChildren(String fPid, String area);
}
