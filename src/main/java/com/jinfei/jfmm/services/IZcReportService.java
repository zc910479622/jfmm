package com.jinfei.jfmm.services;

import com.jinfei.jfmm.model.zcReport;

import java.util.List;

public interface IZcReportService {
    List<zcReport> list();

    List<zcReport> mainList(zcReport z);

    Integer getTotal(zcReport z);
}
