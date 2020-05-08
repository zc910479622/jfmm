package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.BModel;
import com.jinfei.jfmm.services.IBModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bModel")
public class BModelService {
    @Autowired
    private IBModelService ibModelService;

    public List<BModel> list(){
        return ibModelService.selectAll(new BModel());
    }
}
