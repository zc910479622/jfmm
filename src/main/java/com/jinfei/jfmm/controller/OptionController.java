package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.model.aOrg;
import com.jinfei.jfmm.model.kArea;
import com.jinfei.jfmm.model.kSeat;
import com.jinfei.jfmm.model.kShelf;
import com.jinfei.jfmm.services.IAOrgService;
import com.jinfei.jfmm.services.IKAreaService;
import com.jinfei.jfmm.services.IKSeatService;
import com.jinfei.jfmm.services.IKShelfService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("option")
public class OptionController {
    @Resource
    private IAOrgService orgService;
    @Resource
    private IKAreaService areaService;
    @Resource
    private IKShelfService shelfService;
    @Resource
    private IKSeatService seatService;

    @RequestMapping("/ware")
    @ResponseBody
    public List<aOrg> ware(){
        return orgService.getWare();
    }

    @RequestMapping("/area")
    @ResponseBody
    public List<kArea> karea(String fPid){
        return areaService.getOption(fPid);
    }

    @RequestMapping("/shelf")
    @ResponseBody
    public List<kShelf> shelf(String fPid){
        return shelfService.getOption(fPid);
    }

    @RequestMapping("/seat")
    @ResponseBody
    public List<kSeat> seat(String fPid,String state){
        return seatService.getOption(fPid,state);
    }
}
