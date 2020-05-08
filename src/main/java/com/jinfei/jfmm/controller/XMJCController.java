package com.jinfei.jfmm.controller;

import com.alibaba.fastjson.JSON;
import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.IDetl;
import com.jinfei.jfmm.model.IMain;
import com.jinfei.jfmm.model.IMainResult;
import com.jinfei.jfmm.model.User;
import com.jinfei.jfmm.services.IIDetlService;
import com.jinfei.jfmm.services.IIMainService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.openxmlformats.schemas.drawingml.x2006.main.STSystemColorVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("process/XMJC")
@Controller
public class XMJCController {

    @Autowired
    private IIMainService iiMainService;

    @Autowired
    private IIDetlService iiDetlService;

    @RequestMapping("/list")
    public String XMJCList(ModelMap modelMap) {
        return "process/XMJC/List";
    }

    @RequestMapping(value = {"/selectAll"}, method= RequestMethod.POST)
    @ResponseBody
    public List<IMain> selctAll(IMain iMain){
        iMain.setIs_process(1);
        iMain.setF_type("0");
        return iiMainService.selectAll(iMain);
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String add(ModelMap mmap) {
        User user = shiroUtil.getSysUser();        // 根据用户id取出菜单
        mmap.put("user", user);
        return "process/XMJC/add";
    }

    @Log(title = "新增送货单", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/add"}, method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult add(IMainResult mainResult){
        try {
            //取json字符串中的对象
            JSONObject jsonObject= JSONObject.fromObject(mainResult.getImain());
            IMain iMain=(IMain)JSONObject.toBean(jsonObject, IMain.class);
            iMain.setF_id(iMain.getF_id()==null? UUID.randomUUID().toString():iMain.getF_id());
            iMain.setIs_process(1);
            List<IDetl> detls = new ArrayList<IDetl>();

            //取json字符串中的数组
            JSONArray jsonArray= JSONArray.fromObject(mainResult.getDetls());
            for (Object obj:jsonArray) {
                JSONObject jobj = JSONObject.fromObject(obj);
                IDetl detl = (IDetl) JSONObject.toBean(jobj, IDetl.class);
                detl.setF_id(detl.getF_id()==null?UUID.randomUUID().toString():detl.getF_id());
                detl.setMain_id(iMain.getF_id());
                detl.setOrder_no(detl.getOrder_no()==null?0:detl.getOrder_no());
                detl.setRece_time((detl.getRece_time()==null||detl.getRece_time().length()==0)?"0000-00-00 00:00:00":detl.getRece_time());
                detl.setSeat_id(detl.getSeat_id()==null?"":detl.getSeat_id());
                detl.setRece_edit_time(detl.getRece_edit_time()==null?"0000-00-00 00:00:00":detl.getRece_edit_time());
                detl.setImg_path(detl.getImg_path()==null?"":detl.getImg_path());
                detls.add(detl);
            }

            int success = 0;
            System.out.println(iMain);
            success = iiMainService.add(iMain);

            if(success>0){
                if (detls.size()!=0){
                    iiDetlService.add(detls);
                    iiDetlService.saveIMG(detls);
                }
            }else{
                return AjaxResult.error();
            }

            if (success>0){
                return AjaxResult.success();
            }else {
                return AjaxResult.error();
            }

        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error();
        }
    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"},method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, ModelMap modelMap){
        IMain iMain = new IMain();
        iMain.setF_id(fid);
        iMain.setIs_process(1);
        IMainResult mainResult = new IMainResult();
        mainResult.setImain(JSON.toJSONString(iiMainService.selectAll(iMain)));
        IDetl detl = new IDetl();
        detl.setMain_id(fid);
        detl.setOrderByColumn("match_no");
        detl.setIsAsc("asc");
        List<IDetl> detls = iiDetlService.selectAll(detl);
        mainResult.setDetls(JSON.toJSONString(detls));
        modelMap.addAttribute("mainResult",mainResult);
        return "process/XMJC/edit";
    }

    @RequestMapping(value = {"/removeIMain"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult removeIMain(String ids){
        String[] idss = ids.split(",");
        int success = 0;
        try {
            iiDetlService.removeByMainId(idss);

            success = iiMainService.remove(idss);

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

    @RequestMapping(value = {"/removeDetl"},method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult removeDetl(String ids){
//        System.out.println(ids);
        String[] idss = ids.split(",");
        int success = 0;
        try {
            success = iiDetlService.remove(idss);
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

    //跳转部件接收
    @RequestMapping(value = {"/receiveBMain/{id}"},method = RequestMethod.GET)
    public String receiveBMain(@PathVariable("id") String fid, ModelMap modelMap){
        IMain iMain = new IMain();
        iMain.setF_id(fid);
        iMain.setIs_process(1);
        IMainResult mainResult = new IMainResult();
        mainResult.setImain(JSON.toJSONString(iiMainService.selectAll(iMain)));
        IDetl detl = new IDetl();
        detl.setMain_id(fid);
        List<IDetl> detls = iiDetlService.selectAll(detl);
        mainResult.setDetls(JSON.toJSONString(detls));
        modelMap.addAttribute("mainResult",mainResult);
        return "process/XMJC/receiveBMain";
    }

    @RequestMapping(value = {"/ReceiveTo"})
    public String ReceiveTo(){
        return "process/XMJC/Receive";
    }

    @RequestMapping(value = {"/Receive"},method = RequestMethod.POST)
    @ResponseBody
    private AjaxResult Receive(IMain iMain){
        int success = 0;
        try {
            iMain.setIs_process(1);
            success = iiMainService.Receive(iMain);
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
