package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.Staff;

import java.util.List;

public interface IStaffDao {
    List<Staff> selectAll(Staff staff);

    int add(Staff staff);

    int edit(Staff staff);

    int remove(String[] ids);
}
