package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.CommonUtils;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.model.PageHelper;
import com.jinfei.jfmm.model.aDict;
import com.jinfei.jfmm.model.mMatch;
import com.jinfei.jfmm.services.IMMatchService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Mould/Archives")
public class mMatchController {

    private String prefix = "/Mould/Archives";

    @Resource
    private IMMatchService imMatchService;

    @RequiresPermissions("Mould:Archives:view")
    @RequestMapping("")
    public String archives(ModelMap modelMap){
        modelMap.addAttribute("dict",CommonUtils.getDictObj("parts_cate"));
        return prefix+"/archives";
    }

    @RequestMapping(value = "matchList",method = RequestMethod.POST)
    @ResponseBody
    public PageHelper<Map<String,Object>> matchList(mMatch mMatch){
        mMatch.setOffset((mMatch.getPageNum() - 1) * mMatch.getPageSize());
        PageHelper<Map<String,Object>> pageHelper = new PageHelper<>();
        List<mMatch> mMatches =  imMatchService.matchList(mMatch);
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<aDict> aDicts = CommonUtils.getDictObj("parts_cate");
        List<String> strings = new ArrayList<>();
        pageHelper.setTotal(imMatchService.getTotal(mMatch));
        for (mMatch match:mMatches){
            if (strings.indexOf(match.getmMainId())==-1){
                strings.add(match.getmMainId());
            }
        }
        for (String s:strings){
            Map<String,Object>  map = new HashMap<>();
            map.put("mMainId",s);
            for (mMatch match:mMatches){
                if (s.equals(match.getmMainId())){
                    map.put("mMainName",match.getmMainName());
                    map.put("mModelName",match.getmModelName());
                    map.put("compParts",match.getCompParts());
                    for (aDict aDict :aDicts){
                        if (aDict.getfLable().equals(match.getPartsCate())){
                            map.put(aDict.getfValue()+"FId",match.getfId());
                            map.put(aDict.getfValue(),match.getbMatchNo());
                        }
                    }
                }
            }
            mapList.add(map);
        }
        pageHelper.setRows(mapList);
        return pageHelper;
    }

    @RequestMapping(value = "edit/{fId}",method = RequestMethod.GET)
    public String editTo(@PathVariable("fId") String value, ModelMap modelMap){
        System.out.println(value);
        String[] arr = value.split("_");
        modelMap.addAttribute("type",arr[0]);
        modelMap.addAttribute("bMatchNo",arr[1].replaceAll("|","/"));
        modelMap.addAttribute("mMainId",arr[3]);
        modelMap.addAttribute("bModelName",arr[4].replaceAll("|","/"));
        modelMap.addAttribute("prod_cate",arr[5]);
        if (!"undefined".equals(arr[2])) {
            modelMap.addAttribute("fId", arr[2]);
        }else {
            modelMap.addAttribute("fId", "");
        }
        return "Mould/Archives/edit";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @Log(title = "匹配管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult save(mMatch mMatch){
        if (StringUtils.isEmpty(mMatch.getbMatchNo())){
            if (imMatchService.remove(mMatch)){
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        }
        boolean b = imMatchService.checkFid(mMatch.getfId());
        if (b){
            if (imMatchService.update(mMatch)){
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        } else {
            if (imMatchService.insert(mMatch)){
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        }
    }
}
