package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.model.sysOperLog;
import com.jinfei.jfmm.services.ISysOperLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/Basic/Oper")
public class OperLogController {

    private String prefix = "/Basic/Oper";

    @Resource
    private ISysOperLogService operLogService;

    @RequiresPermissions("Basic:Oper:view")
    @RequestMapping("")
    public String oper(){
        return prefix+"/oper";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<sysOperLog> list(sysOperLog operLog)
    {
        List<sysOperLog> list = operLogService.selectOperLogList(operLog);
        return list;
    }

    @RequestMapping("/clean")
    @Log(title = "操作日志管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult clean()
    {
        operLogService.cleanOperLog();
        return AjaxResult.success();
    }
}
