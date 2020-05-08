package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.Staff;

import java.util.List;

public interface IStaffService {
    List<Staff> selectAll(Staff staff);

    int add(Staff staff);

    int edit(Staff staff);

    int remove(String[] ids);
}
