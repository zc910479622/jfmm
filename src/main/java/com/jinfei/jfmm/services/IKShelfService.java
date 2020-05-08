package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.kShelf;

import java.util.List;

public interface IKShelfService {
    List<kShelf> list();

    Object getKShelf(String s);

    boolean addSave(kShelf kShelf);

    boolean editSave(kShelf kShelf);

    boolean remove(String s);

    List<kShelf> getOption(String fPid);

    List<kShelf> list(String shelf);

    List<kShelf> getChildren(String fPid, String shelf);

    void editIsStatus(String fId);
}
