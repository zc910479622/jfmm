package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.model.BModel;
import com.jinfei.jfmm.model.mCost;
import com.jinfei.jfmm.services.IBModelService;
import com.jinfei.jfmm.services.IMCostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Mould/Cost")
public class mCostController {

    private String prefix = "/Mould/Cost";

    @Autowired
    private IBModelService ibModelService;

    @Autowired
    private IMCostService imCostService;

    @RequiresPermissions("Mould:Cost:view")
    @RequestMapping("")
    public String cost() {
        return prefix + "/cost";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<mCost> list() {
        return imCostService.list();
    }

    @RequestMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequestMapping("addSave")
    @ResponseBody
    public AjaxResult addSave(mCost mCost) {
        if (imCostService.check(mCost)) {
            return AjaxResult.error(500, "存在重复类型,寸位");
        } else {
            if (imCostService.add(mCost)) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        }
    }

    @RequestMapping("/edit/{data}")
    public String edit(@PathVariable("data") String data, ModelMap modelMap) {
        String[] arr = data.split(",");
        mCost mCost = imCostService.getCost(arr[0], arr[1], arr[2]);
        System.out.println(mCost.getwCost());
        modelMap.addAttribute("cost", mCost);
        return prefix + "/edit";
    }

    @RequestMapping("editSave")
    @ResponseBody
    public AjaxResult editSave(mCost mCost) {
        if (imCostService.edit(mCost)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }

    }

    @RequestMapping("remove/{data}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("data") String data) {
        String[] arr = data.split(",");
        Boolean b = imCostService.remove(arr[0], arr[1], arr[2]);
        if (b) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @RequestMapping("/getCost")
    @ResponseBody
    public String getCost(String modelId, String cast_type_id) {
        mCost mCost = imCostService.getCostOf(modelId, cast_type_id);

        BModel bModel = ibModelService.getBModel(modelId);

        if ("SM".equals(bModel.getClassify_no()) && StringUtils.isNotNull(mCost)) {
            return (String.format("%.2f", Double.valueOf(mCost.getwCost() * mCost.getSmProp() / 100)).toString());
        } else if ("XM".equals(bModel.getClassify_no()) && StringUtils.isNotNull(mCost)) {
            return (String.format("%.2f", Double.valueOf(mCost.getwCost() * mCost.getXmProp() / 100)).toString());
        } else if ("BM".equals(bModel.getClassify_no()) && StringUtils.isNotNull(mCost)) {
            return (String.format("%.2f", Double.valueOf(mCost.getwCost() * mCost.getBmProp() / 100)).toString());
        } else if ("MJ".equals(bModel.getClassify_no()) && StringUtils.isNotNull(mCost)) {
            return (String.format("%.2f", Double.valueOf(mCost.getwCost() * mCost.getMjProp() / 100)).toString());
        } else {
            return "";
        }
    }
}
