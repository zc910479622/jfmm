package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.annotation.Log;
import com.jinfei.jfmm.common.base.AjaxResult;
import com.jinfei.jfmm.common.base.BaseException;
import com.jinfei.jfmm.common.enums.BusinessType;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.model.aOrg;
import com.jinfei.jfmm.model.kArea;
import com.jinfei.jfmm.model.kSeat;
import com.jinfei.jfmm.model.kShelf;
import com.jinfei.jfmm.services.IAOrgService;
import com.jinfei.jfmm.services.IKAreaService;
import com.jinfei.jfmm.services.IKSeatService;
import com.jinfei.jfmm.services.IKShelfService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Basic/Ware")
public class WareController extends BaseException {

    private String prefix = "/Basic/Ware";

    @Resource
    private IAOrgService iaOrgService;

    @Resource
    private IKAreaService ikAreaService;

    @Resource
    private IKShelfService ikShelfService;

    @Resource
    private IKSeatService ikSeatService;

    @RequiresPermissions("Basic:Ware:view")
    @RequestMapping("")
    public String ware() {
        return prefix + "/ware";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Map<String, Object>> list(String ware, String area, String shelf, String seat) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<aOrg> aOrgs = iaOrgService.getWareB(ware);
        List<kArea> kAreas = ikAreaService.list(area);
        for (aOrg aOrg : aOrgs) {
            Map<String, Object> map = new HashMap<>();
            map.put("fId", aOrg.getfId());
            map.put("fName", aOrg.getfName());
            map.put("fPid", "");
            map.put("type", "1");
            mapList.add(map);
            for (kArea kArea : kAreas) {
                if (kArea.getHouseId().equals(aOrg.getfId())) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("fId", kArea.getfId());
                    map1.put("fName", kArea.getfName());
                    map1.put("fPid", kArea.getHouseId());
                    map1.put("type", "2");
                    mapList.add(map1);
                }
            }
        }
        return mapList;
    }

    @RequestMapping("/childrenList")
    @ResponseBody
    public List<Map<String, Object>> childrenList(String fPid, String type,String ware, String area, String shelf, String seat) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        if ("1".equals(type)) {
            List<kArea> kAreas = ikAreaService.getChildren(fPid,area);
            List<kShelf> kShelfs = ikShelfService.list(shelf);
            for (kArea kArea : kAreas) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("fId", kArea.getfId());
                map1.put("fName", kArea.getfName());
                map1.put("fPid", kArea.getHouseId());
                map1.put("state", kArea.getIs_state());
                map1.put("type", "2");
                mapList.add(map1);
                for (kShelf kShelf : kShelfs) {
                    if (kShelf.getAreaId().equals(kArea.getfId())) {
                        Map<String, Object> map2 = new HashMap<>();
                        map2.put("fId", kShelf.getfId());
                        map2.put("fName", kShelf.getfName());
                        map2.put("fPid", kShelf.getAreaId());
                        map2.put("state", kShelf.getIs_state());
                        map2.put("type", "3");
                        mapList.add(map2);
                    }
                }
            }
        } else if ("2".equals(type)) {
            List<kShelf> kShelfs = ikShelfService.getChildren(fPid,shelf);
            List<kSeat> kSeats = ikSeatService.getSeatWare(seat);
            for (kShelf kShelf : kShelfs) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("fId", kShelf.getfId());
                map2.put("fName", kShelf.getfName());
                map2.put("fPid", kShelf.getAreaId());
                map2.put("state", kShelf.getIs_state());
                map2.put("type", "3");
                mapList.add(map2);
                for (kSeat kSeat : kSeats) {
                    if (kSeat.getShelfId().equals(kShelf.getfId())) {
                        Map<String, Object> map3 = new HashMap<>();
                        map3.put("fId", kSeat.getfId());
                        map3.put("fName", kSeat.getfName());
                        map3.put("fPid", kSeat.getShelfId());
                        map3.put("state", kSeat.getIs_state());
                        map3.put("type", "4");
                        mapList.add(map3);
                    }
                }
            }
        } else if ("3".equals(type)) {
            List<kSeat> kSeats = ikSeatService.getChildren(fPid,seat);
            for (kSeat kSeat : kSeats) {
                Map<String, Object> map3 = new HashMap<>();
                map3.put("fId", kSeat.getfId());
                map3.put("fName", kSeat.getfName());
                map3.put("fPid", kSeat.getShelfId());
                map3.put("state", kSeat.getIs_state());
                map3.put("type", "4");
                mapList.add(map3);
            }
        }
        return mapList;
    }

    //跳转新增页面
    @RequestMapping(value = {"/add/{fId}"}, method = RequestMethod.GET)
    public String add(@PathVariable("fId") String fId, ModelMap modelMap) {
        String[] arr = fId.split(",");
        if ("1".equals(arr[1])) {
            modelMap.addAttribute("ware", iaOrgService.getAOrg(arr[0]));
            modelMap.addAttribute("type", "1");
        } else if ("2".equals(arr[1])) {
            modelMap.addAttribute("ware", ikAreaService.getKArea(arr[0]));
            modelMap.addAttribute("type", "2");
        } else if ("3".equals(arr[1])) {
            modelMap.addAttribute("ware", ikShelfService.getKShelf(arr[0]));
            modelMap.addAttribute("type", "3");
        } else if ("4".equals(arr[1])) {
            modelMap.addAttribute("ware", ikSeatService.getKSeat(arr[0]));
            modelMap.addAttribute("type", "4");
        }
        return "Basic/Ware/add";
    }

    //新增保存
    @RequiresPermissions("Basic:Ware:add")
    @Log(title = "库区管理", businessType = BusinessType.INSERT)
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(HttpServletRequest request) {
        String fId = request.getParameter("fId");
        String fName = request.getParameter("fName");
        String fPid = request.getParameter("fPid");
        String type = request.getParameter("type");
        Boolean status = Integer.parseInt(request.getParameter("status")) == 1;
        boolean boo = false;
        if ("1".equals(type)) {
            boo = ikAreaService.addSave(new kArea(fId, fName, fPid,status));
        } else if ("2".equals(type)) {
            boo = ikShelfService.addSave(new kShelf(fId, fName, fPid,status));
        } else if ("3".equals(type)) {
            boo = ikSeatService.addSave(new kSeat(fId, fPid, fName, status));
        }
        try {
            if (boo) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            return AjaxResult.error();
        }

    }

    //跳转修改页面
    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
    public String edit(@PathVariable("id") String fid, ModelMap modelMap) {
        String[] arr = fid.split(",");
        if ("1".equals(arr[1])) {
            modelMap.addAttribute("ware", iaOrgService.getAOrg(arr[0]));
            modelMap.addAttribute("type", "1");
        } else if ("2".equals(arr[1])) {
            kArea kArea = (kArea) ikAreaService.getKArea(arr[0]);
            modelMap.addAttribute("ware", kArea);
            modelMap.addAttribute("fWare", iaOrgService.getAOrg(kArea.getHouseId()));
            modelMap.addAttribute("type", "2");
        } else if ("3".equals(arr[1])) {
            kShelf kShelf = (kShelf) ikShelfService.getKShelf(arr[0]);
            modelMap.addAttribute("ware", kShelf);
            modelMap.addAttribute("fWare", ikAreaService.getKArea(kShelf.getAreaId()));
            modelMap.addAttribute("type", "3");
        } else if ("4".equals(arr[1])) {
            kSeat kSeat = (kSeat) ikSeatService.getKSeat(arr[0]);
            modelMap.addAttribute("ware", kSeat);
            modelMap.addAttribute("fWare", ikShelfService.getKShelf(kSeat.getShelfId()));
            modelMap.addAttribute("type", "4");
        }
        return "Basic/Ware/edit";
    }

    //修改保存
    @RequiresPermissions("Basic:Ware:edit")
    @Log(title = "库区管理", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult editSave(HttpServletRequest request) {
        String fId = request.getParameter("fId");
        String fName = request.getParameter("fName");
        String fPid = request.getParameter("fPid");
        String type = request.getParameter("type");
        Boolean status = Integer.parseInt(request.getParameter("status")) == 1;
        boolean boo = false;
        if ("2".equals(type)) {
            boo = ikAreaService.editSave(new kArea(fId, fName, fPid,status));
            if (!status){
                ikShelfService.editIsStatus(fId);
                ikSeatService.editIsStatusAll(fId);
            }
        } else if ("3".equals(type)) {
            boo = ikShelfService.editSave(new kShelf(fId, fName, fPid,status));
            if (!status){
                ikSeatService.editIsStatus(fId);
            }
        } else if ("4".equals(type)) {
            boo = ikSeatService.editSave(new kSeat(fId, fPid, fName, status));
        }
        if (boo) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    //删除
    @RequiresPermissions("Basic:Ware:remove")
    @Log(title = "库区管理", businessType = BusinessType.DELETE)
    @RequestMapping(value = {"/remove/{fId}"}, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult remove(@PathVariable("fId") String fId) {
        String[] arr = fId.split(",");
        boolean boo = false;
        if ("2".equals(arr[1])) {
            boo = ikAreaService.remove(arr[0]);
        } else if ("3".equals(arr[1])) {
            boo = ikShelfService.remove(arr[0]);
        } else if ("4".equals(arr[1])) {
            boo = ikSeatService.remove(arr[0]);
        }
        if (boo) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 选择库区树
     */
    @RequestMapping("/selectWareTree/{fId}")
    public String selectDeptTree(@PathVariable("fId") String fId, ModelMap modelMap) {
        String[] arr = fId.split(",");
        if ("1".equals(arr[1])) {
            modelMap.addAttribute("ware", iaOrgService.getAOrg(arr[0]));
            modelMap.addAttribute("type", "1");
        } else if ("2".equals(arr[1])) {
            modelMap.addAttribute("ware", ikAreaService.getKArea(arr[0]));
            modelMap.addAttribute("type", "2");
        } else if ("3".equals(arr[1])) {
            modelMap.addAttribute("ware", ikShelfService.getKShelf(arr[0]));
            modelMap.addAttribute("type", "3");
        } else if ("4".equals(arr[1])) {
            modelMap.addAttribute("ware", ikSeatService.getKSeat(arr[0]));
            modelMap.addAttribute("type", "4");
        }
        return "Basic/Ware/tree";
    }

    /**
     * 选择库区树
     */
    @RequestMapping("/selectWareBTree/{fId}")
    public String selectDeptBTree(@PathVariable("fId") String fId, ModelMap modelMap) {
        String[] arr = fId.split(",");
        modelMap.addAttribute("aOrg", arr[0]);
        modelMap.addAttribute("bMainId", arr[1]);
        return "Basic/WareB/tree";
    }

    //获取全部货位信息
    @RequestMapping(value = "treeWare")
    @ResponseBody
    public List<Map<String, Object>> treeWare() {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<aOrg> aOrgs = iaOrgService.getWare();
        List<kArea> kAreas = ikAreaService.list();
        List<kShelf> kShelfs = ikShelfService.list();
        List<kSeat> kSeats = ikSeatService.list();
        for (aOrg aOrg : aOrgs) {
            Map<String, Object> map = new HashMap<>();
            map.put("fId", aOrg.getfId());
            map.put("fName", aOrg.getfName());
            map.put("fPid", "");
            map.put("type", "1");
            mapList.add(map);
        }
        for (kArea kArea : kAreas) {
            Map<String, Object> map = new HashMap<>();
            map.put("fId", kArea.getfId());
            map.put("fName", kArea.getfName());
            map.put("fPid", kArea.getHouseId());
            map.put("type", "2");
            mapList.add(map);
        }
        for (kShelf kShelf : kShelfs) {
            Map<String, Object> map = new HashMap<>();
            map.put("fId", kShelf.getfId());
            map.put("fName", kShelf.getfName());
            map.put("fPid", kShelf.getAreaId());
            map.put("type", "3");
            mapList.add(map);
        }
        for (kSeat kSeat : kSeats) {
            if (kSeat.getIs_state()) {
                Map<String, Object> map = new HashMap<>();
                map.put("fId", kSeat.getfId());
                map.put("fName", kSeat.getfName());
                map.put("fPid", kSeat.getShelfId());
                map.put("type", "4");
                mapList.add(map);
            }
        }
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        trees = getTrees(mapList, false, null);
        return trees;
    }

    //获取根据组织机构获取货位信息
    @RequestMapping(value = "treeWareB/{Id}")
    @ResponseBody
    public List<Map<String, Object>> treeWareB(@PathVariable("Id") String Id) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        aOrg aOrg = iaOrgService.getAOrg(Id);
        List<kArea> kAreas = ikAreaService.list();
        List<kShelf> kShelfs = ikShelfService.list();
        List<kSeat> kSeats = ikSeatService.list();
        if (StringUtils.isNotNull(aOrg)) {
            Map<String, Object> map = new HashMap<>();
            map.put("fId", aOrg.getfId());
            map.put("fName", aOrg.getfName());
            map.put("fPid", "");
            map.put("type", "1");
            mapList.add(map);
            for (kArea kArea : kAreas) {
                if (aOrg.getfId().equals(kArea.getHouseId())) {
                    Map<String, Object> kAreaMap = new HashMap<>();
                    kAreaMap.put("fId", kArea.getfId());
                    kAreaMap.put("fName", kArea.getfName());
                    kAreaMap.put("fPid", kArea.getHouseId());
                    kAreaMap.put("type", "2");
                    mapList.add(kAreaMap);
                    for (kShelf kShelf : kShelfs) {
                        if (kArea.getfId().equals(kShelf.getAreaId())) {
                            Map<String, Object> kShelfMap = new HashMap<>();
                            kShelfMap.put("fId", kShelf.getfId());
                            kShelfMap.put("fName", kShelf.getfName());
                            kShelfMap.put("fPid", kShelf.getAreaId());
                            kShelfMap.put("type", "3");
                            mapList.add(kShelfMap);
                            for (kSeat kSeat : kSeats) {
                                if (kShelf.getfId().equals(kSeat.getShelfId()) && kSeat.getIs_state()) {
                                    Map<String, Object> kSeatMap = new HashMap<>();
                                    kSeatMap.put("fId", kSeat.getfId());
                                    kSeatMap.put("fName", kSeat.getfName());
                                    kSeatMap.put("fPid", kSeat.getShelfId());
                                    kSeatMap.put("type", "4");
                                    mapList.add(kSeatMap);
                                }
                            }
                        }
                    }
                }
            }
        }


        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        trees = getTrees(mapList, false, null);
        return trees;
    }



    /**
     * 对象转库树
     *
     * @param mapList      库列表
     * @param isCheck      是否需要选中
     * @param roleWareList 角色已存在库区列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<Map<String, Object>> mapList, boolean isCheck, List<String> roleWareList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : mapList) {
            Map<String, Object> watrtMap = new HashMap<String, Object>();
            watrtMap.put("id", map.get("fId"));
            watrtMap.put("pId", map.get("fPid"));
            watrtMap.put("name", map.get("fName"));
            watrtMap.put("title", map.get("fName"));
            watrtMap.put("type", map.get("type"));
            if (isCheck) {
                watrtMap.put("checked", roleWareList.contains((String) map.get("fId") + (String) map.get("fname")));
            } else {
                watrtMap.put("checked", false);
            }
            trees.add(watrtMap);
        }
        return trees;
    }
}
