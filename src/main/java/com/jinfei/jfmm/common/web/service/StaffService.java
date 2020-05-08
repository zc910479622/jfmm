package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.Staff;
import com.jinfei.jfmm.services.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("staff")
public class StaffService {
    @Autowired
    private IStaffService staffService;

    public List<Staff> getStaffList(){
        return staffService.selectAll(new Staff());
    }
}
