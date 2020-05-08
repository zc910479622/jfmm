package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.IStaffDao;
import com.jinfei.jfmm.model.Staff;
import com.jinfei.jfmm.services.IStaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StaffServiceImpl implements IStaffService {

    @Resource
    private IStaffDao staffDao;

    @Override
    public List<Staff> selectAll(Staff staff) {
        return staffDao.selectAll(staff);
    }

    @Override
    public int add(Staff staff) {
        return staffDao.add(staff);
    }

    @Override
    public int edit(Staff staff) {
        return staffDao.edit(staff);
    }

    @Override
    public int remove(String[] ids) {
        return staffDao.remove(ids);
    }
}
