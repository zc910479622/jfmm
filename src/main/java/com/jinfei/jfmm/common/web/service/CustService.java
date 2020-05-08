package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.aCust;
import com.jinfei.jfmm.services.IACustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cust")
public class CustService {

    @Autowired
    private IACustService custService;

    public List<aCust> getCustList(){
        return custService.list(new aCust());
    }
}
