package com.jinfei.jfmm.dao;

import com.jinfei.jfmm.model.zcReport;

import java.util.List;

public interface zcReportMapper {
    List<zcReport> list();

    List<zcReport> mainList(zcReport z);

    Integer getTotal(zcReport z);
}