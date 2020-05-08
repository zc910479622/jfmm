package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.kSeat;

import java.util.List;

public interface IKSeatService {
    List<kSeat> list();


    Object getKSeat(String s);

    boolean addSave(kSeat kSeat);

    boolean editSave(kSeat kSeat);

    boolean remove(String s);

    List<kSeat> getOption(String fPid, String state);

    List<kSeat> list(String seat);

    List<kSeat> getSeatWare(String seat);

    List<kSeat> getChildren(String fPid, String seat);

    void editIsStatus(String fId);

    void editIsStatusAll(String fId);

}
