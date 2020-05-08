package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.aSupp;
import com.jinfei.jfmm.services.IASuppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("supp")
public class SuppService {

    @Autowired
    private IASuppService suppService;

    public List<aSupp> list(){
        return suppService.list(new aSupp());
    }
}
