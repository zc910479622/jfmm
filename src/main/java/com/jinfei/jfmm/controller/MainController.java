package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.CommonUtils;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.*;
import com.jinfei.jfmm.services.IMainService;
import com.jinfei.jfmm.services.ISRecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@RequestMapping("/Basic/Main")
@Controller
public class MainController {

    private String prefix = "/Basic/Main";

    @Resource
    private IMainService mainService;

    @Resource
    private ISRecordService isRecordService;

    @RequiresPermissions("Basic:Main:view")
    @RequestMapping("")
    public String main() {
        return prefix + "/mainTZ";
    }

    @RequestMapping("/selectAll")
    @ResponseBody
//    private List<Main> selectAll(Main main){
//        System.out.println(main);
//        return mainService.selectAll(main);
//    }
    public PageHelper<mainTZ> selectTZAll(mainTZ main, HttpServletRequest request) {
        main.setOffset((main.getPageNum() - 1) * main.getPageSize());
        PageHelper<mainTZ> pageHelper = new PageHelper<>();
        Integer total = mainService.getTotal(main);
        Boolean is_pop = request.getParameter("is_pop") == null ? false : Boolean.valueOf(request.getParameter("is_pop"));//区分是否窗口传值，过滤流转中的部件
        main.setIs_pop(is_pop);
        try {
            List<mainTZ> mainTZS = new ArrayList<>();
            request.setCharacterEncoding("UTF-8");
            Boolean selectByMould = request.getParameter("selectByMould") == null ? false : Boolean.valueOf(request.getParameter("selectByMould"));//区分是否根据整模流转查询部件
            if (selectByMould) {
                SelectByMould select = new SelectByMould(request.getParameter("mould_id"), request.getParameter("classify_no"),is_pop);
                mainTZS = mainService.selectByMould(select);
                total = mainService.getTotalByMould(select);
                pageHelper.setTotal(total);
                pageHelper.setRows(mainTZS);
                return pageHelper;
            }
            mainTZS = mainService.selectTZAll(main);
            pageHelper.setTotal(total);
            pageHelper.setRows(mainTZS);
            return pageHelper;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new PageHelper<>();
    }

    @RequestMapping(value = {"/add"})
    public String add(){
        return prefix+"/add";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult add(Main main) {
        main.setF_id(UUID.randomUUID().toString());
        int success = 0;
        try {
            success = mainService.add(main);
        } catch (Exception e) {
            e.printStackTrace();
            success = 0;
        }

        if (success > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = {"/addMainTZ"}, method = RequestMethod.POST)
    @Log(title = "部件管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public AjaxResult addMainTZ(@RequestParam("mainArray") String main) {
        JSONArray jsonArray = JSONArray.fromObject(main);
        List<mainTZ> mainTZS = new ArrayList<>();
//        main.setF_id(UUID.randomUUID().toString());
        int success = 0;
//        try {
//            success = mainService.addMainTZ(main);
//        } catch (Exception e) {
//            e.printStackTrace();
//            success = 0;
//        }
        for (Object obj:jsonArray){
            JSONObject jobj = JSONObject.fromObject(obj);
            mainTZ maintz = (mainTZ) JSONObject.toBean(jobj,mainTZ.class);
            maintz.setF_id(UUID.randomUUID().toString());
            mainTZS.add(maintz);
        }
//        System.out.println(mainTZS);
        try {
            success = mainService.addMainTZs(mainTZS);
        } catch (Exception e) {
            e.printStackTrace();
            success = 0;
        }
        if (success > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, ModelMap modelMap) {
        Main main = new Main();
        main.setF_id(fid);
        modelMap.addAttribute("main", mainService.selectAll(main).get(0));
        return "Basic/Main/edit";
    }

    //执行修改业务
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult edit(Main main) {
        int success = 0;
        try {
            success = mainService.edit(main);
        } catch (Exception e) {
            e.printStackTrace();
            success = 0;
        }
        if (success > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //跳转修改页面
    @RequestMapping(value = {"/editMainTZ/{id}"}, method = RequestMethod.GET)
    public String editMainTZ(@PathVariable("id") String fid, ModelMap modelMap) {
        mainTZ main = new mainTZ();
        main.setF_id(fid);
//        modelMap.addAttribute("main", mainService.selectAll(main).get(0));
        modelMap.addAttribute("mainTZ", mainService.selectTZAll(main).get(0));
        return "Basic/Main/edit";
    }

    //执行修改业务
    @RequestMapping(value = {"/editMainTZ"}, method = RequestMethod.POST)
    @Log(title = "部件管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editMainTZ(mainTZ main) {
        int success = 0;
        try {
            success = mainService.editMainTZ(main);
        } catch (Exception e) {
            e.printStackTrace();
            success = 0;
        }
        if (success > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //部件存放
    @RequestMapping(value = "/seatUpdate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult seatUpdate(@RequestParam("seat") String seat, @RequestParam("f_id") String f_id) {
        int success = 0;
        String[] arr = f_id.split(",");
        for (String s : arr) {
            mainTZ mainTz = mainService.getMainTZ(s);
            if (mainService.updateRack(seat, s,mainTz.getRequest_org())) {
                sRecord sRecord = new sRecord();
                sRecord.setfId(UUID.randomUUID().toString());
                sRecord.setbMainId(s);
                sRecord.setDeptId(mainTz.getLocation());
                sRecord.setOutSeatId("");
                sRecord.setInSeatId(seat);
                sRecord.setEditUser(shiroUtil.getUserId());
                isRecordService.insert(sRecord);
                success++;
            }
        }
        if (success == arr.length) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = {"/remove"}, method = RequestMethod.POST)
    @Log(title = "部件管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult remove(String ids) {
        String[] idss = ids.split(",");
        int success = 0;
        try {
            success = mainService.remove(idss);
        } catch (Exception e) {
            e.printStackTrace();
            success = 0;
        }
        if (success > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @RequestMapping(value = {"/selectTZByMouldId"}, method = RequestMethod.POST)
    @ResponseBody
    public List<mainTZ> selectTZByMouldId(String mouldID){
        return mainService.selectTZByMouldId(mouldID);
    }

    @RequestMapping(value = {"/selectMatchNo"}, method = RequestMethod.POST)
    @ResponseBody
    public List<mainTZ> selectPopAll(mainTZ mainTZ) {
        List<aDict> aDicts = CommonUtils.getDictObj("parts_cate");
        for (com.jinfei.jfmm.model.aDict aDict : aDicts) {
            if (mainTZ.getF_name().equals(aDict.getfValue())) {
                mainTZ.setF_name(aDict.getfLable());
            }
        }
        return mainService.selectMatchNo(mainTZ);
    }


    public static Map<String, Object> getParameter(String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            final String charset = "utf-8";
            url = URLDecoder.decode(url, charset);
            if (url.indexOf('?') != -1) {
                final String contents = url.substring(url.indexOf('?') + 1);
                String[] keyValues = contents.split("&");
                for (int i = 0; i < keyValues.length; i++) {
                    String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                    String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
