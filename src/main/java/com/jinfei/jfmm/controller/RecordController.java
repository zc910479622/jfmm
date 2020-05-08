package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.model.sRecord;
import com.jinfei.jfmm.services.ISRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Basic/Record")
public class RecordController {

    private  String prefix = "/Basic/Record";

    @Autowired
    private ISRecordService isRecordService;

    @RequiresPermissions("Basic:WareB:view")
    @RequestMapping("")
    public String recode(){
        return prefix+"/record";
    }

    @RequiresPermissions("Basic:WareB:list")
    @RequestMapping("/list")
    @ResponseBody
    public List<sRecord> list(){
        return isRecordService.list();
    }
}
