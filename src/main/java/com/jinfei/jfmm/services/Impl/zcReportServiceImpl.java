package com.jinfei.jfmm.services.Impl;

import com.jinfei.jfmm.dao.zcReportMapper;
import com.jinfei.jfmm.model.zcReport;
import com.jinfei.jfmm.services.IZcReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class zcReportServiceImpl implements IZcReportService {

    @Autowired(required = false)
    private zcReportMapper zcReportMapper;

    @Override
    public List<zcReport> list() {
        return zcReportMapper.list();
    }

    @Override
    public List<zcReport> mainList(zcReport z) {
        return zcReportMapper.mainList(z);
    }

    @Override
    public Integer getTotal(zcReport z) {
        return zcReportMapper.getTotal(z);
    }
}
