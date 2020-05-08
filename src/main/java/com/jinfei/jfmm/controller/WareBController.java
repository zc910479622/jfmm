package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.CommonUtils;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.common.utils.shiroUtil;
import com.jinfei.jfmm.model.*;
import com.jinfei.jfmm.services.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Basic/WareB")
public class WareBController extends BaseException {

    private String prefix = "/Basic/WareB";

    @Resource
    private IAOrgService iaOrgService;

    @Resource
    private IKAreaService ikAreaService;

    @Resource
    private IKShelfService ikShelfService;

    @Resource
    private IKSeatService ikSeatService;

    @Resource
    private IMainService mainService;

    @Resource
    private ISRecordService isRecordService;

    @RequiresPermissions("Basic:WareB:view")
    @RequestMapping("")
    public String wareB() {
        return prefix + "/wareb";
    }

    @RequiresPermissions("Basic:WareB:list")
    @RequestMapping("/list")
    @ResponseBody
    public List<Map<String, Object>> list(String ware, String area, String shelf, String seat, String stat) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<aOrg> aOrgs = iaOrgService.getWareB(ware);
        List<kArea> kAreas = ikAreaService.list(area);
        List<kShelf> kShelfs = ikShelfService.list(shelf);
        List<kSeat> kSeats = ikSeatService.list(seat);
        List<mainTZ> mains = mainService.getSeat();
        for (com.jinfei.jfmm.model.aOrg aOrg : aOrgs) {
            for (com.jinfei.jfmm.model.kArea kArea : kAreas) {
                if (kArea.getHouseId().equals(aOrg.getfId())) {
                    for (kShelf kShelf : kShelfs) {
                        if (kShelf.getAreaId().equals(kArea.getfId())) {
                            for (kSeat kSeat : kSeats) {
                                if (kSeat.getShelfId().equals(kShelf.getfId())) {
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("aOrgName", aOrg.getfName());
                                    map.put("aOrgId", aOrg.getfId());
                                    map.put("areaName", kArea.getfName());
                                    map.put("areaId", kArea.getfId());
                                    map.put("shelfName", kShelf.getfName());
                                    map.put("shelfId", kShelf.getfId());
                                    map.put("seatName", kSeat.getfName());
                                    map.put("seatId", kSeat.getfId());
                                    map.put("stat", false);
                                    for (com.jinfei.jfmm.model.mainTZ mainTZ : mains) {
                                        if (kSeat.getfId().equals(mainTZ.getRack_position())) {
                                            map.put("stat", true);
                                            break;
                                        }
                                    }
                                    if (StringUtils.isNotEmpty(stat)) {
                                        if ("1".equals(stat)) {
                                            if ((Boolean) map.get("stat")) {
                                                mapList.add(map);
                                            }
                                        } else {
                                            if (!(Boolean) map.get("stat")) {
                                                mapList.add(map);
                                            }
                                        }
                                    } else {
                                        mapList.add(map);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return mapList;
    }

    @RequestMapping("/bMainList")
    @ResponseBody
    public PageHelper<mainTZ> bmainlist(String aOrg, String area, String shelf, String seat, String type, Integer row, Integer page) {
        PageHelper<mainTZ> pageHelper = new PageHelper<>();
        List<mainTZ> list = new ArrayList<>();
        if ("1".equals(type)) {
            list = mainService.selectbMainList(aOrg, type, row, page);
            pageHelper.setTotal(mainService.getMatchTotal(aOrg, type));
        } else if ("2".equals(type)) {
            list = mainService.selectbMainList(area, type, row, page);
            pageHelper.setTotal(mainService.getMatchTotal(area, type));
        } else if ("3".equals(type)) {
            list = mainService.selectbMainList(shelf, type, row, page);
            pageHelper.setTotal(mainService.getMatchTotal(shelf, type));
        } else if ("4".equals(type)) {
            list = mainService.selectbMainList(seat, type, row, page);
            pageHelper.setTotal(mainService.getMatchTotal(seat, type));
        }
        List<aDict> assets = CommonUtils.getDictObj("asset_states");
        for (com.jinfei.jfmm.model.mainTZ mainTZ : list) {
            for (com.jinfei.jfmm.model.aDict aDict : assets) {
                if (aDict.getfValue().equals(mainTZ.getAssets_state()))
                    mainTZ.setAssets_state(aDict.getfLable());
            }
            if (StringUtils.isNotEmpty(mainTZ.getLocation()))
                mainTZ.setLocation(CommonUtils.getfName(mainTZ.getLocation()));
        }
        pageHelper.setRows(list);
        return pageHelper;
    }


    @RequestMapping("/storeBModel{id}")
    public String storeBModel(@PathVariable("id") String id, ModelMap modelMap) {
        String[] arr = id.split(",");
        modelMap.addAttribute("orgId", arr[0]);
        modelMap.addAttribute("seatId", arr[1]);
        return "/Pop/popTzCopy";
    }

    @RequiresPermissions("Basic:WareB:update")
    @Log(title = "库区部件,库位变更", businessType = BusinessType.UPDATE)
    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(@RequestParam("seat") String seat, @RequestParam("bMainId") String bMainId) {
        if (mainService.updateRack(seat, bMainId, "")) {
            mainTZ mainTz = mainService.getMainTZ(bMainId);
            sRecord sRecord = new sRecord();
            sRecord.setbMainId(bMainId);
            sRecord.setDeptId(mainTz.getLocation());
            sRecord.setOutSeatId(mainTz.getRack_position());
            sRecord.setInSeatId(seat);
            sRecord.setEditUser(shiroUtil.getUserId());
            isRecordService.insert(sRecord);
            return AjaxResult.success();
        } else {
            return AjaxResult.error("库位变更失败");
        }
    }

}
