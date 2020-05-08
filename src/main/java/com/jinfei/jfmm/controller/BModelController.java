package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.model.BModel;
import com.jinfei.jfmm.model.PageHelper;
import com.jinfei.jfmm.services.IBModelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = {"/Basic/BModel"})
@Controller
public class BModelController {

    private String prefix = "/Basic/BModel";

    @Resource
    private IBModelService ibModelService;

    @RequiresPermissions("Basic:BModel:view")
    @RequestMapping("")
    public String BModel(){
        return prefix+"/BModel";
    }

    @RequestMapping(value = "/selectAll")
    @ResponseBody
    public PageHelper<BModel> selectAll(BModel bModel){
        if (bModel.getPageNum()!=null&&bModel.getPageSize()!=null){
            bModel.setOffset((bModel.getPageNum()-1)*bModel.getPageSize());
        }
        PageHelper<BModel> pageHelper = new PageHelper<>();
        Integer total = ibModelService.getTotal(bModel);
        pageHelper.setTotal(total);
        List<BModel> bModels = ibModelService.selectAll(bModel);
        for (BModel bModel1 : bModels){
            System.out.println(bModel1.getClassify_no());
        }
        pageHelper.setRows(bModels);
        return pageHelper;
    }


    @RequestMapping("/add")
    public String add(){
        return prefix+"/add";
    }

    //新增数据
    @Log(title = "部件型号管理", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/addSave"}, method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(BModel bModel){
        bModel.setF_id(UUID.randomUUID().toString());
        int success = 0;
        try {
            success = ibModelService.add(bModel);
        }catch (Exception e){
            e.printStackTrace();
            success = 0;
        }

        if (success>0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"},method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, ModelMap modelMap){
        BModel bModel = new BModel();
        bModel.setF_id(fid);
        modelMap.addAttribute("bModel",ibModelService.selectAll(bModel).get(0));
        return "Basic/BModel/edit";
    }

    //执行修改业务
    @Log(title = "部件型号管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/edit"}, method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult edit(BModel bModel){
        int success = 0;
        try {
            success = ibModelService.edit(bModel);
        }catch (Exception e){
            e.printStackTrace();
            success = 0;
        }
        if (success>0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    //移除数据
    @Log(title = "部件型号管理", businessType = BusinessType.DELETE)
    @RequestMapping(value = {"/remove"}, method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(String ids){
        String[] idss = ids.split(",");
        int success = 0;
        try {
            success = ibModelService.remove(idss);
        }catch (Exception e){
            e.printStackTrace();
            success = 0;
        }
        if (success>0){
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }
}
