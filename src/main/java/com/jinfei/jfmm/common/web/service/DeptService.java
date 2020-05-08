package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.aOrg;
import com.jinfei.jfmm.services.IAOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dept")
public class DeptService {
    @Autowired
    private IAOrgService orgService;

    public String getfName(String fId) {
        try {
            aOrg aOrg = orgService.selectByPrimaryKey(fId);
            return aOrg.getfName();
        } catch (Exception e) {
            return "";
        }
    }

    public List<aOrg> deptList(){
        return orgService.list(new aOrg());
    }

}
