package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.model.BModel;
import com.jinfei.jfmm.model.PageHelper;
import com.jinfei.jfmm.model.mMain;
import com.jinfei.jfmm.model.mModel;
import com.jinfei.jfmm.services.IBModelService;
import com.jinfei.jfmm.services.IMMainService;
import com.jinfei.jfmm.services.IMMatchService;
import com.jinfei.jfmm.services.IMModelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/Mould/Main")
public class mMainController {

    private String prefix = "/Mould/Main";

    @Resource
    private IMMainService imMainService;

    @Resource
    private IMModelService imModelService;

    @Resource
    private IBModelService ibModelService;

    @Resource
    private IMMatchService imMatchService;

    @RequiresPermissions("Mould:Main:view")
    @RequestMapping("")
    public String main(){
        return prefix+"/main";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageHelper<mMain> list(mMain main){
        main.setOffset((main.getPageNum()-1)*main.getPageSize());
        PageHelper<mMain> pageHelper = new PageHelper<>();
        Integer total = imMainService.getTotal(main);
        pageHelper.setTotal(total);
        pageHelper.setRows(imMainService.list(main));

        return pageHelper;
    }

    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public mMain selectByPrimaryKey(mMain main){
        return imMainService.selectByPrimaryKey(main.getfId());
    }

    //跳转新增页面
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String add() {
        return "Mould/Main/add";
    }

    //新增保存
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    @Log(title = "模具编号", businessType = BusinessType.INSERT)
    @ResponseBody
    public AjaxResult addSave(mMain main) {
        try {
            if (imMainService.insertMain(main)) {
                mModel mModel = (com.jinfei.jfmm.model.mModel) imModelService.getModel(main.getModelId());
                List<BModel> bModels = new ArrayList<>();
                String[] classifys = main.getComp_parts().split(",");
                for (String classify:classifys){
                    BModel bModel = new BModel();
                    bModel.setF_id(UUID.randomUUID().toString());
                    bModel.setF_name(mModel.getfName()+"-"+classify);
                    bModel.setProd_classify_id(mModel.getProdClassifyId());
                    bModel.setProd_spec_id(mModel.getProd_spec_id());
                    bModel.setClassify_no(classify);
                    bModel.setMould_spec_id(mModel.getSpecId());
                    bModels.add(bModel);
                }
                try {
                    ibModelService.addByMMain(bModels);
                }catch (Exception e){
//                    e.printStackTrace();
                    return AjaxResult.success();
                }
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }

        } catch (Exception e) {
//            e.printStackTrace();
            return AjaxResult.error();
        }

    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, HttpServletRequest request) {
        request.setAttribute("mMain", imMainService.getMain(fid));
        return "Mould/Main/edit";
    }

    //修改保存
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @Log(title = "模具编号", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(mMain main) {

        if (imMainService.updateMain(main)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequestMapping(value = {"/remove/{fId}"}, method = RequestMethod.POST)
    @Log(title = "模具编号", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("fId") String fId) {
        if (imMainService.delete(fId)) {
            imMatchService.delete(fId);
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = {"/remove"}, method= RequestMethod.POST)
    @Log(title = "模具编号", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult removeAll(String ids){
        String[] idss = ids.split(",");
        int success = 0;
        try {
            success = imMainService.removeAll(idss);
            if (success>0){
                return AjaxResult.success();
            }else{
                return AjaxResult.error();
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = "checkFNo",method = RequestMethod.POST)
    @ResponseBody
    public String checkFNo(@RequestParam String fId, String fNo) throws IOException {
        String jsonResult = "";
        if (fNo != null) {
            boolean b = this.imMainService.checkFNo(fId, fNo);
            if (b) {
                jsonResult = "{\"valid\":false}";
            } else {
                jsonResult = "{\"valid\":true}";
            }
        } else {
            jsonResult = "{\"valid\":false}";
        }
        return jsonResult;
    }

    @RequestMapping(value = {"/getMMainById"})
    @ResponseBody
    public mMain getMMainById(@RequestParam String fId){
        return (mMain) imMainService.getMain(fId);
    }

    @RequestMapping(value = {"/amortization"})
    @ResponseBody
    public AjaxResult amortization(String ids,String amortization){
        String a = "0";
        if ("false".equals(amortization)){
            a = "1";
        }
        Boolean b = imMainService.setAmortization(ids,a);
        if (b){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = {"/amortizationTime"})
    @ResponseBody
    public AjaxResult amortizationTime(String ids,String amortizationTime){
        String a;
        if (StringUtils.isNotEmpty(amortizationTime)){
            a = amortizationTime;
        }else {
            a = null;
        }
        Boolean b = imMainService.setAmortizationTime(ids,a);
        if (b){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }
}
