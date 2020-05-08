package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.model.mModel;
import com.jinfei.jfmm.services.IMModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MModel")
public class MModelService {

    @Autowired
    private IMModelService modelService;

    public String getMouldName(String fId){
        mModel mModel = (mModel) modelService.getModel(fId);
        if (StringUtils.isNull(mModel)){
            return "";
        }else {
            return mModel.getfName();
        }
    }
}
