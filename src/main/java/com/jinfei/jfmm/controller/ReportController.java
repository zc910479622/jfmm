package com.jinfei.jfmm.controller;

import com.jinfei.jfmm.common.utils.CommonUtils;
import com.jinfei.jfmm.common.utils.DateUtils;
import com.jinfei.jfmm.common.utils.StringUtils;
import com.jinfei.jfmm.model.*;
import com.jinfei.jfmm.services.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Report")
public class ReportController {

    @Resource
    private IMainService mainService;

    @Resource
    private IZcReportService iZcReportService;

    @Resource
    private IMMainService imMainService;

    @Resource
    private IAOrgService iaOrgService;

    @Resource
    private IKSeatService ikSeatService;

    @RequestMapping("/report")
    @RequiresPermissions("Report:report:view")
    public String report() {
        return "/Report/report";
    }

    @RequestMapping("/report/list")
    @ResponseBody
    public PageHelper<Map<String, Object>> list(zcReport z) {
        PageHelper<Map<String, Object>> pageHelper = new PageHelper<>();
        z.setOffset((z.getPageNum() - 1) * z.getPageSize());
        Integer Total = iZcReportService.getTotal(z);
        List<zcReport> mainList = iZcReportService.mainList(z);
        List<zcReport> list = iZcReportService.list();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (zcReport zcReport : mainList) {
            Map<String, Object> map = new HashMap<>();
            map.put("mainName", zcReport.getmMainName());
            map.put("modelName", zcReport.getmModelName());
            map.put("mainNums", zcReport.getmMainNums());
            map.put("assetsState", zcReport.getAssetsState());
            map.put("location", zcReport.getLocation());
            map.put("requestOrg", zcReport.getRequestOrg());
            map.put("amortization", zcReport.getAmortization());
            map.put("amortizationTime", zcReport.getAmortizationTime());
            for (zcReport zc : list) {
                if (zcReport.getmMainName().equals(zc.getmMainName()) && zcReport.getLocation().equals(zc.getLocation())) {
                    String[] arr = zc.getPartName().split("-");
                    if (arr[arr.length - 1].equals("BM")) {
                        map.put("BM", zc.getPartName());
                        map.put("BM_rack_position", zc.getRackPosition());
                        map.put("BM_part_nums", zc.getPartNums());
                    } else if (arr[arr.length - 1].equals("MJ")) {
                        map.put("MJ", zc.getPartName());
                        map.put("MJ_rack_position", zc.getRackPosition());
                        map.put("MJ_part_nums", zc.getPartNums());
                    } else if (arr[arr.length - 1].equals("SM")) {
                        map.put("SM", zc.getPartName());
                        map.put("SM_rack_position", zc.getRackPosition());
                        map.put("SM_part_nums", zc.getPartNums());
                    } else if (arr[arr.length - 1].equals("XM")) {
                        map.put("XM", zc.getPartName());
                        map.put("XM_rack_position", zc.getRackPosition());
                        map.put("XM_part_nums", zc.getPartNums());
                    }
                }
            }
            mapList.add(map);
        }
        pageHelper.setTotal(Total);
        pageHelper.setRows(mapList);
        return pageHelper;
    }

    /**
     * 报表
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/report/execl")
    @ResponseBody
    public void ProdCateExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<aDict> prod_spec = CommonUtils.getDictObj("prod_spec");//字典 产品尺寸
        List<aDict> prod_cate = CommonUtils.getDictObj("prod_cate");//字典 产品类别
        List<aDict> parts_cate = CommonUtils.getDictObj("parts_cate");//字典 模具类别

        List<zcReport> mainList = iZcReportService.mainList(new zcReport());
        List<zcReport> list = iZcReportService.list();
        List<Map<String, Object>> AllList = new ArrayList<>();
        List<Map<String, Object>> trialList = new ArrayList<>();
        List<Map<String, Object>> MassList = new ArrayList<>();
        List<Map<String, Object>> scrapList = new ArrayList<>();
        for (zcReport zcReport : mainList) {
            Map<String, Object> map = new HashMap<>();
            map.put("mainName", zcReport.getmMainName());
            map.put("modelName", zcReport.getmModelName());
            map.put("mainNums", zcReport.getmMainNums());
            map.put("assetsState", zcReport.getAssetsState());
            map.put("location", zcReport.getLocation());
            map.put("requestOrg", zcReport.getRequestOrg());
            map.put("amortization", zcReport.getAmortization());
            map.put("castTypeId", zcReport.getCastTypeId());
            map.put("prodSpecId", zcReport.getProdSpecId());
            map.put("wCost", zcReport.getwCost());
            for (zcReport zc : list) {
                if (zcReport.getmMainName().equals(zc.getmMainName()) && zcReport.getLocation().equals(zc.getLocation())) {
                    String[] arr = zc.getPartName().split("-");
                    if (arr[arr.length - 1].equals("BM")) {
                        map.put("BM", zc.getPartName());
                        map.put("BM_rack_position", zc.getRackPosition());
                        map.put("BM_part_nums", zc.getPartNums());
                    } else if (arr[arr.length - 1].equals("MJ")) {
                        map.put("MJ", zc.getPartName());
                        map.put("MJ_rack_position", zc.getRackPosition());
                        map.put("MJ_part_nums", zc.getPartNums());
                    } else if (arr[arr.length - 1].equals("SM")) {
                        map.put("SM", zc.getPartName());
                        map.put("SM_rack_position", zc.getRackPosition());
                        map.put("SM_part_nums", zc.getPartNums());
                    } else if (arr[arr.length - 1].equals("XM")) {
                        map.put("XM", zc.getPartName());
                        map.put("XM_rack_position", zc.getRackPosition());
                        map.put("XM_part_nums", zc.getPartNums());
                    }
                }
            }
            if ("1".equals(zcReport.getAssetsState())) {
                trialList.add(map);
            } else if ("2".equals(zcReport.getAssetsState())) {
                MassList.add(map);
            } else if ("3".equals(zcReport.getAssetsState())) {
                scrapList.add(map);
            }
            AllList.add(map);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(("ZCBB" + DateUtils.dateTimeNow() + ".xls").getBytes(), "ISO-8859-1"));
        HSSFSheet sheet = wb.createSheet("模具类型产品寸位数量汇总台账");
        sheet.setDefaultColumnWidth(14);
        sheet.setDefaultRowHeight((short) 900);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);   //设置/自动换行2
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setWrapText(true);   //设置/自动换行2
        style1.setBorderBottom(BorderStyle.THIN); //下边框
        style1.setBorderLeft(BorderStyle.THIN);//左边框
        style1.setBorderTop(BorderStyle.THIN);//上边框
        style1.setBorderRight(BorderStyle.THIN);//右边框
        style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFSheet sheet1 = wb.createSheet("试制");
        HSSFSheet sheet2 = wb.createSheet("量产");
        HSSFSheet sheet3 = wb.createSheet("报废");
        HSSFSheet sheet4 = wb.createSheet("模具类型产品数量汇总台账");
        setSheet0(sheet, style, style1, AllList);
        setSheet1(sheet1, style, trialList);
        setSheet1(sheet2, style, MassList);
        setSheet1(sheet3, style, scrapList);
        setSheet2(sheet4, style, style1, AllList);
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    private void dg(List<aOrg> a, List<aOrg> aOrgList, String fPid) {
        for (aOrg aOrg : aOrgList) {
            if (fPid.equals(aOrg.getfPid())) {
                a.add(aOrg);
                dg(a, aOrgList, aOrg.getfId());
            }
        }
    }

    private void setSheet0(HSSFSheet sheet, HSSFCellStyle style, HSSFCellStyle style1, List<Map<String, Object>> AllList) {
        List<aDict> asset_states = CommonUtils.getDictObj("asset_states");//字典资产状态
        List<aDict> cast_type = CommonUtils.getDictObj("cast_type");//字典资产状态
        List<aDict> prod_spec = CommonUtils.getDictObj("prod_spec");//字典资产状态
        List<aOrg> divisionList = iaOrgService.divisionList();
        List<aOrg> aOrgList = iaOrgService.list(new aOrg());
        List<List<aOrg>> locationList = new ArrayList<>();
        int row = 3;
        int cell = 8 + (asset_states.size() == 0 ? 1 : asset_states.size());
        for (int i = 0; i < row; i++) {
            sheet.createRow(i);
            for (int j = 0; j <= cell; j++) {
                sheet.getRow(i).createCell(j).setCellStyle(style);
            }
            if (i == 0) {
                sheet.getRow(0).setHeight((short) 900);
                sheet.getRow(0).getCell(0).setCellValue(new HSSFRichTextString("模具资产管理台账汇总表"));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cell));
            }
            if (i == 1) {
                sheet.getRow(1).setHeight((short) 900);
                sheet.getRow(1).getCell(0).setCellValue(new HSSFRichTextString("序号"));
                sheet.getRow(1).getCell(1).setCellValue(new HSSFRichTextString("事业部"));
                sheet.getRow(1).getCell(2).setCellValue(new HSSFRichTextString("模具存放地点"));
                sheet.getRow(1).getCell(3).setCellValue(new HSSFRichTextString("模具类型"));
                sheet.getRow(1).getCell(4).setCellValue(new HSSFRichTextString("寸位"));
                sheet.getRow(1).getCell(5).setCellValue(new HSSFRichTextString("模具总数 （已报废不算入总数内）"));
                sheet.getRow(1).getCell(6).setCellValue(new HSSFRichTextString("各模具资产状态在位数量情况"));
                sheet.getRow(1).getCell(6 + asset_states.size()).setCellValue(new HSSFRichTextString("模具开模费（元）"));
                sheet.getRow(1).getCell(7 + asset_states.size()).setCellValue(new HSSFRichTextString("已摊销数量"));
                sheet.getRow(1).getCell(8 + asset_states.size()).setCellValue(new HSSFRichTextString("未摊销数量"));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 5 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 6 + asset_states.size(), 6 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 7 + asset_states.size(), 7 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 8 + asset_states.size(), 8 + asset_states.size()));
            }
            if (i == 2) {
                for (int a = 0; a < asset_states.size(); a++) {
                    sheet.getRow(2).getCell(6 + a).setCellValue(new HSSFRichTextString(asset_states.get(a).getfLable()));
                }
            }
        }
        for (aOrg aOrg : divisionList) {
            List<aOrg> a = new ArrayList<>();
            dg(a, aOrgList, aOrg.getfId());
            row += a.size() * cast_type.size() * prod_spec.size();
            locationList.add(a);
        }
        int i = 3;
        int ii = 3;
        int hjsz = 0;
        int hjlc = 0;
        int hjbf = 0;
        Double hjfy = 0.0;
        int hjytx = 0;
        int hjwtx = 0;
        for (int j = 0; j < locationList.size(); j++) {
            int xjsz = 0;
            int xjlc = 0;
            int xjbf = 0;
            Double xjfy = 0.0;
            int xjytx = 0;
            int xjwtx = 0;
            for (int k = 0; k < locationList.get(j).size(); k++) {
                for (aDict castType : cast_type) {
                    if (locationList.get(j).get(k).getfName().indexOf("电轮") == -1 && castType.getfValue().equals("2")) {
                        continue;
                    }
                    for (aDict prodSpec : prod_spec) {
                        sheet.createRow(i);
                        sheet.getRow(i).setHeight((short) 600);
                        for (int c = 0; c <= cell; c++) {
                            sheet.getRow(i).createCell(c).setCellStyle(style);
                        }
                        sheet.getRow(i).getCell(0).setCellValue(ii - 2);
                        sheet.getRow(i).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(j).getfName()));
                        sheet.getRow(i).getCell(2).setCellValue(new HSSFRichTextString(locationList.get(j).get(k).getfName()));
                        sheet.getRow(i).getCell(3).setCellValue(new HSSFRichTextString(castType.getfLable()));
                        sheet.getRow(i).getCell(4).setCellValue(new HSSFRichTextString(prodSpec.getfLable()));

                        int shizhi = 0;
                        int liangchang = 0;
                        int baofei = 0;
                        int yitanxiao = 0;
                        int weitanxiao = 0;
                        int zongshu = 0;
                        Double feiyong = 0.00;
                        for (Map<String, Object> map : AllList) {
                            if (prodSpec.getfValue().equals(map.get("prodSpecId"))
                                    && castType.getfValue().equals(map.get("castTypeId"))
                                    && map.get("location").equals(locationList.get(j).get(k).getfId())) {
                                if ("1".equals(map.get("assetsState"))) {
                                    shizhi += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                                } else if ("2".equals(map.get("assetsState"))) {
                                    liangchang += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                                } else if ("3".equals(map.get("assetsState"))) {
                                    baofei += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                                }
                                feiyong += Double.valueOf(StringUtils.isNotNull(map.get("wCost")) ? map.get("wCost").toString() : "0") * Double.valueOf(map.get("mainNums").toString());
                                if ((Boolean) map.get("amortization")) {
                                    yitanxiao += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                                } else {
                                    weitanxiao += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                                }
                            }
                        }
                        xjbf += baofei;
                        xjlc += liangchang;
                        xjsz += shizhi;
                        xjfy += feiyong;
                        xjytx += yitanxiao;
                        xjwtx += weitanxiao;
                        hjbf += baofei;
                        hjlc += liangchang;
                        hjsz += shizhi;
                        hjfy += feiyong;
                        hjytx += yitanxiao;
                        hjwtx += weitanxiao;
                        sheet.getRow(i).getCell(5).setCellValue(shizhi + liangchang + baofei);
                        sheet.getRow(i).getCell(6).setCellValue(shizhi);
                        sheet.getRow(i).getCell(7).setCellValue(liangchang);
                        sheet.getRow(i).getCell(8).setCellValue(baofei);
                        sheet.getRow(i).getCell(6 + asset_states.size()).setCellValue(feiyong);
                        sheet.getRow(i).getCell(7 + asset_states.size()).setCellValue(yitanxiao);
                        sheet.getRow(i).getCell(8 + asset_states.size()).setCellValue(weitanxiao);
                        i++;
                        ii++;
                    }
                }
            }
            sheet.createRow(i);
            for (int c = 0; c <= cell; c++) {
                sheet.getRow(i).createCell(c).setCellStyle(style1);
            }
            sheet.getRow(i).getCell(5).setCellValue(xjbf + xjlc + xjsz);
            sheet.getRow(i).getCell(6).setCellValue(xjsz);
            sheet.getRow(i).getCell(7).setCellValue(xjlc);
            sheet.getRow(i).getCell(8).setCellValue(xjbf);
            sheet.getRow(i).getCell(6 + asset_states.size()).setCellValue(xjfy);
            sheet.getRow(i).getCell(7 + asset_states.size()).setCellValue(xjytx);
            sheet.getRow(i).getCell(8 + asset_states.size()).setCellValue(xjwtx);
            sheet.getRow(i).getCell(0).setCellValue(new HSSFRichTextString("小计"));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 4));
            i++;
        }
        sheet.createRow(i);
        for (int c = 0; c <= cell; c++) {
            sheet.getRow(i).createCell(c).setCellStyle(style1);
        }
        sheet.getRow(i).getCell(5).setCellValue(hjbf + hjlc + hjsz);
        sheet.getRow(i).getCell(6).setCellValue(hjsz);
        sheet.getRow(i).getCell(7).setCellValue(hjlc);
        sheet.getRow(i).getCell(8).setCellValue(hjbf);
        sheet.getRow(i).getCell(6 + asset_states.size()).setCellValue(hjfy);
        sheet.getRow(i).getCell(7 + asset_states.size()).setCellValue(hjytx);
        sheet.getRow(i).getCell(8 + asset_states.size()).setCellValue(hjwtx);
        sheet.getRow(i).getCell(0).setCellValue(new HSSFRichTextString("合计"));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 4));
        i++;
    }

    /*private void setSheet0(HSSFSheet sheet, HSSFCellStyle style, List<Map<String, Object>> AllList) {
        List<aDict> asset_states = CommonUtils.getDictObj("asset_states");//字典资产状态
        List<aDict> cast_type = CommonUtils.getDictObj("cast_type");//字典资产状态
        List<aDict> prod_spec = CommonUtils.getDictObj("prod_spec");//字典资产状态
        List<aOrg> divisionList = iaOrgService.divisionList();
        List<aOrg> aOrgList = iaOrgService.list(new aOrg());
        List<List<aOrg>> locationList = new ArrayList<>();
        String location = "";
        String cast_type_value = "";
        String prod_spec_value = "";
        int row = 3;
        int cell = 8 + (asset_states.size() == 0 ? 1 : asset_states.size());
        for (aOrg aOrg : divisionList) {
            List<aOrg> a = new ArrayList<>();
            dg(a, aOrgList, aOrg.getfId());
            row += a.size() * cast_type.size() * prod_spec.size();
            locationList.add(a);
        }
        for (int i = 0; i < row; i++) {
            sheet.createRow(i);
            for (int j = 0; j <= cell; j++) {
                sheet.getRow(i).createCell(j).setCellStyle(style);
            }
            if (i == 0) {
                sheet.getRow(0).setHeight((short) 900);
                sheet.getRow(0).getCell(0).setCellValue(new HSSFRichTextString("模具资产管理台账汇总表"));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cell));
            }
            if (i == 1) {
                sheet.getRow(1).setHeight((short) 900);
                sheet.getRow(1).getCell(0).setCellValue(new HSSFRichTextString("序号"));
                sheet.getRow(1).getCell(1).setCellValue(new HSSFRichTextString("事业部"));
                sheet.getRow(1).getCell(2).setCellValue(new HSSFRichTextString("模具存放地点"));
                sheet.getRow(1).getCell(3).setCellValue(new HSSFRichTextString("模具类型"));
                sheet.getRow(1).getCell(4).setCellValue(new HSSFRichTextString("寸位"));
                sheet.getRow(1).getCell(5).setCellValue(new HSSFRichTextString("模具总数 （已报废不算入总数内）"));
                sheet.getRow(1).getCell(6).setCellValue(new HSSFRichTextString("各模具资产状态在位数量情况"));
                sheet.getRow(1).getCell(6 + asset_states.size()).setCellValue(new HSSFRichTextString("模具开模费（元）"));
                sheet.getRow(1).getCell(7 + asset_states.size()).setCellValue(new HSSFRichTextString("已摊销数量"));
                sheet.getRow(1).getCell(8 + asset_states.size()).setCellValue(new HSSFRichTextString("未摊销数量"));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 5 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 6 + asset_states.size(), 6 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 7 + asset_states.size(), 7 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 8 + asset_states.size(), 8 + asset_states.size()));
            }
            if (i == 2) {
                for (int a = 0; a < asset_states.size(); a++) {
                    sheet.getRow(2).getCell(6 + a).setCellValue(new HSSFRichTextString(asset_states.get(a).getfLable()));
                }
            }
            if (i > 2) {
                sheet.getRow(i).getCell(0).setCellValue((i - 2));
                if (i >= 3 && i <3 + prod_spec.size() * cast_type.size() * locationList.get(0).size()) {
                    sheet.getRow(i).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(0).getfName()));
                }else if (i >=3 + prod_spec.size() * cast_type.size() * locationList.get(0).size() && i <3 + prod_spec.size() * cast_type.size() * (locationList.get(0).size()+locationList.get(1).size())){
                    sheet.getRow(i).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(1).getfName()));
                }else if (i >=3 + prod_spec.size() * cast_type.size() * (locationList.get(0).size()+locationList.get(1).size()) && i <3 + prod_spec.size() * cast_type.size() * (locationList.get(0).size()+locationList.get(1).size()+locationList.get(2).size())){
                    sheet.getRow(i).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(2).getfName()));
                }
                int aa = 0;
                for (int j = 0; j < locationList.size(); j++) {
                    for (int k = 0; k < locationList.get(j).size(); k++) {
                        if (i >= (3 + prod_spec.size() * cast_type.size() * aa) && i < i + 3 + prod_spec.size() * cast_type.size() - 1) {
                            location = locationList.get(j).get(k).getfId();
                            sheet.getRow(i).getCell(2).setCellValue(new HSSFRichTextString(locationList.get(j).get(k).getfName()));
                        }
                        aa++;
                    }
                }
                for (int j = 0; j < cast_type.size(); j++) {
                    if ((i) % (cast_type.size() * prod_spec.size()) >= (3 + prod_spec.size() * j)) {
                        cast_type_value = cast_type.get(j).getfValue();
                        sheet.getRow(i).getCell(3).setCellValue(new HSSFRichTextString(cast_type.get(j).getfLable()));
                    }
                }
                prod_spec_value = prod_spec.get((i - 3) % prod_spec.size()).getfValue();
                sheet.getRow(i).getCell(4).setCellValue(new HSSFRichTextString(prod_spec.get((i - 3) % prod_spec.size()).getfLable()));
                int shizhi = 0;
                int liangchang = 0;
                int baofei = 0;
                int yitanxiao = 0;
                int weitanxiao = 0;
                int zongshu = 0;
                Double feiyong = 0.00;
                for (Map<String, Object> map : AllList) {
                    if (map.get("castTypeId").equals(cast_type_value) && map.get("prodSpecId").equals(prod_spec_value) && map.get("location").equals(location)) {
                        if ("1".equals(map.get("assetsState"))) {
                            shizhi += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                        } else if ("2".equals(map.get("assetsState"))) {
                            liangchang += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                        } else if ("3".equals(map.get("assetsState"))) {
                            baofei += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                        }
                        feiyong += Double.valueOf(StringUtils.isNotNull(map.get("partPrice")) ? map.get("partPrice").toString() : "0") * Double.valueOf(map.get("mainNums").toString());
                        if ("0".equals(map.get("amortization"))) {
                            weitanxiao += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                        } else {
                            yitanxiao += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                        }
                    }
                }
                sheet.getRow(i).getCell(5).setCellValue(shizhi + liangchang + baofei);
                sheet.getRow(i).getCell(6).setCellValue(shizhi);
                sheet.getRow(i).getCell(7).setCellValue(liangchang);
                sheet.getRow(i).getCell(8).setCellValue(baofei);
                sheet.getRow(i).getCell(6 + asset_states.size()).setCellValue(feiyong);
                sheet.getRow(i).getCell(7 + asset_states.size()).setCellValue(yitanxiao);
                sheet.getRow(i).getCell(8 + asset_states.size()).setCellValue(weitanxiao);
            }
        }
        *//*for (int j = 0; j < divisionList.size(); j++) {
            if (j == 0) {
                sheet.getRow(3).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(j).getfName()));
                sheet.addMergedRegion(new CellRangeAddress(3, 3 + prod_spec.size() * cast_type.size() * locationList.get(j).size() - 1, 1, 1));
                Double SUM = 0.0;
                for (int a = 3;a<3 + prod_spec.size() * cast_type.size() * locationList.get(j).size() - 1;a++){
                    SUM+=Double.valueOf(sheet.getRow(a).getCell(5).toString());
                }
                sheet.getRow(3).getCell(8 + asset_states.size()).setCellValue(SUM);
                sheet.addMergedRegion(new CellRangeAddress(3, 3 + prod_spec.size() * cast_type.size() * locationList.get(j).size() - 1, 8 + asset_states.size(), 8 + asset_states.size()));
            }
            if (j == 1) {
                sheet.getRow(3 + locationList.get(j - 1).size() * prod_spec.size() * cast_type.size()).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(j).getfName()));
                sheet.addMergedRegion(new CellRangeAddress(3 + locationList.get(j - 1).size() * prod_spec.size() * cast_type.size(), 3 + (locationList.get(j - 1).size() + locationList.get(j).size()) * prod_spec.size() * cast_type.size() - 1, 1, 1));
                Double SUM = 0.0;
                for (int a = 3 + locationList.get(j - 1).size() * prod_spec.size() * cast_type.size();a<3 + (locationList.get(j - 1).size() + locationList.get(j).size()) * prod_spec.size() * cast_type.size() - 1;a++){
                    SUM+=Double.valueOf(sheet.getRow(a).getCell(5).toString());
                }
                sheet.getRow(3 + locationList.get(j - 1).size() * prod_spec.size() * cast_type.size()).getCell(8 + asset_states.size()).setCellValue(SUM);
                sheet.addMergedRegion(new CellRangeAddress(3 + locationList.get(j - 1).size() * prod_spec.size() * cast_type.size(), 3 + (locationList.get(j - 1).size() + locationList.get(j).size()) * prod_spec.size() * cast_type.size() - 1,  8 + asset_states.size(), 8 + asset_states.size()));
            }
            if (j == 2) {
                sheet.getRow(3 + locationList.get(j - 1).size() * prod_spec.size() * cast_type.size() + locationList.get(j - 2).size() * prod_spec.size() * cast_type.size()).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(j).getfName()));
                sheet.addMergedRegion(new CellRangeAddress(3 + (locationList.get(j - 1).size() + locationList.get(j - 2).size()) * prod_spec.size() * cast_type.size(), 3 + (locationList.get(j).size() + locationList.get(j - 1).size() + locationList.get(j - 2).size()) * prod_spec.size() * cast_type.size() - 1, 1, 1));
                Double SUM = 0.0;
                for (int a = 3 + (locationList.get(j - 1).size() + locationList.get(j - 2).size()) * prod_spec.size() * cast_type.size();a<3 + (locationList.get(j).size() + locationList.get(j - 1).size() + locationList.get(j - 2).size()) * prod_spec.size() * cast_type.size() - 1;a++){
                    SUM+=Double.valueOf(sheet.getRow(a).getCell(5).toString());
                }
                sheet.getRow(3 + locationList.get(j - 1).size() * prod_spec.size() * cast_type.size() + locationList.get(j - 2).size() * prod_spec.size() * cast_type.size()).getCell(8 + asset_states.size()).setCellValue(SUM);
                sheet.addMergedRegion(new CellRangeAddress(3 + (locationList.get(j - 1).size() + locationList.get(j - 2).size()) * prod_spec.size() * cast_type.size(), 3 + (locationList.get(j).size() + locationList.get(j - 1).size() + locationList.get(j - 2).size()) * prod_spec.size() * cast_type.size() - 1,  8 + asset_states.size(), 8 + asset_states.size()));
            }
        }*//*
    }*/

    private void setSheet1(HSSFSheet sheet, HSSFCellStyle style, List<Map<String, Object>> List) {
        List<aOrg> aOrgList = iaOrgService.list(new aOrg());
        List<kSeat> seats = ikSeatService.list();
        int row = List.size();
        int cell = 16;
        for (int i = 0; i < row; i++) {
            sheet.createRow(i);
            for (int j = 0; j <= cell; j++) {
                sheet.getRow(i).createCell(j).setCellStyle(style);
            }
            if (i == 0) {
                sheet.getRow(0).setHeight((short) 900);
                sheet.getRow(0).getCell(0).setCellValue(new HSSFRichTextString("仓库"));
                sheet.getRow(0).getCell(1).setCellValue(new HSSFRichTextString("模具编号"));
                sheet.getRow(0).getCell(2).setCellValue(new HSSFRichTextString("模具型号"));
                sheet.getRow(0).getCell(3).setCellValue(new HSSFRichTextString("模具数量"));
                sheet.getRow(0).getCell(4).setCellValue(new HSSFRichTextString("已摊销"));
                sheet.getRow(0).getCell(5).setCellValue(new HSSFRichTextString("边模"));
                sheet.getRow(0).getCell(6).setCellValue(new HSSFRichTextString("库位号"));
                sheet.getRow(0).getCell(7).setCellValue(new HSSFRichTextString("数量"));
                sheet.getRow(0).getCell(8).setCellValue(new HSSFRichTextString("模架"));
                sheet.getRow(0).getCell(9).setCellValue(new HSSFRichTextString("库位号"));
                sheet.getRow(0).getCell(10).setCellValue(new HSSFRichTextString("数量"));
                sheet.getRow(0).getCell(11).setCellValue(new HSSFRichTextString("上模"));
                sheet.getRow(0).getCell(12).setCellValue(new HSSFRichTextString("库位号"));
                sheet.getRow(0).getCell(13).setCellValue(new HSSFRichTextString("数量"));
                sheet.getRow(0).getCell(14).setCellValue(new HSSFRichTextString("下模"));
                sheet.getRow(0).getCell(15).setCellValue(new HSSFRichTextString("库位号"));
                sheet.getRow(0).getCell(16).setCellValue(new HSSFRichTextString("数量"));
            }
            if (i >= 1) {
                for (aOrg aOrg : aOrgList) {
                    if (aOrg.getfId().equals(List.get(i - 1).get("location"))) {
                        sheet.getRow(i).getCell(0).setCellValue(new HSSFRichTextString(aOrg.getfName()));
                    }
                }
                for (kSeat kSeat : seats) {
                    if (kSeat.getfId().equals(List.get(i - 1).get("BM_rack_position"))) {
                        sheet.getRow(i).getCell(6).setCellValue(new HSSFRichTextString(kSeat.getfName()));
                    }
                    if (kSeat.getfId().equals(List.get(i - 1).get("MJ_rack_position"))) {
                        sheet.getRow(i).getCell(9).setCellValue(new HSSFRichTextString(kSeat.getfName()));
                    }
                    if (kSeat.getfId().equals(List.get(i - 1).get("SM_rack_position"))) {
                        sheet.getRow(i).getCell(12).setCellValue(new HSSFRichTextString(kSeat.getfName()));
                    }
                    if (kSeat.getfId().equals(List.get(i - 1).get("XM_rack_position"))) {
                        sheet.getRow(i).getCell(15).setCellValue(new HSSFRichTextString(kSeat.getfName()));
                    }
                }
                sheet.getRow(i).getCell(1).setCellValue(new HSSFRichTextString((String) List.get(i - 1).get("mainName")));
                sheet.getRow(i).getCell(2).setCellValue(new HSSFRichTextString((String) List.get(i - 1).get("modelName")));
                sheet.getRow(i).getCell(3).setCellValue((Integer) List.get(i - 1).get("mainNums"));
                sheet.getRow(i).getCell(4).setCellValue(new HSSFRichTextString((Boolean) List.get(i - 1).get("amortization") ? "已摊销" : "未摊销"));
                sheet.getRow(i).getCell(5).setCellValue(new HSSFRichTextString((String) List.get(i - 1).get("BM")));
                sheet.getRow(i).getCell(7).setCellValue((String) List.get(i - 1).get("BM_part_nums"));
                sheet.getRow(i).getCell(8).setCellValue(new HSSFRichTextString((String) List.get(i - 1).get("MJ")));
                sheet.getRow(i).getCell(10).setCellValue((String) List.get(i - 1).get("MJ_part_nums"));
                sheet.getRow(i).getCell(11).setCellValue(new HSSFRichTextString((String) List.get(i - 1).get("SM")));
                sheet.getRow(i).getCell(13).setCellValue((String) List.get(i - 1).get("SM_part_nums"));
                sheet.getRow(i).getCell(14).setCellValue(new HSSFRichTextString((String) List.get(i - 1).get("XM")));
                sheet.getRow(i).getCell(16).setCellValue((String) List.get(i - 1).get("XM_part_nums"));
            }
        }
    }

    private void setSheet2(HSSFSheet sheet, HSSFCellStyle style, HSSFCellStyle style1, List<Map<String, Object>> allList) {
        List<aDict> asset_states = CommonUtils.getDictObj("asset_states");//字典资产状态
        List<aDict> cast_type = CommonUtils.getDictObj("cast_type");//字典资产状态
        List<aDict> prod_spec = CommonUtils.getDictObj("prod_spec");//字典资产状态
        List<aOrg> divisionList = iaOrgService.divisionList();
        List<aOrg> aOrgList = iaOrgService.list(new aOrg());
        List<List<aOrg>> locationList = new ArrayList<>();
        int row = 3;
        int cell = 7 + (asset_states.size() == 0 ? 1 : asset_states.size());
        for (int i = 0; i < row; i++) {
            sheet.createRow(i);
            for (int j = 0; j <= cell; j++) {
                sheet.getRow(i).createCell(j).setCellStyle(style);
            }
            if (i == 0) {
                sheet.getRow(0).setHeight((short) 900);
                sheet.getRow(0).getCell(0).setCellValue(new HSSFRichTextString("模具资产管理台账汇总表"));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cell));
            }
            if (i == 1) {
                sheet.getRow(1).setHeight((short) 900);
                sheet.getRow(1).getCell(0).setCellValue(new HSSFRichTextString("序号"));
                sheet.getRow(1).getCell(1).setCellValue(new HSSFRichTextString("事业部"));
                sheet.getRow(1).getCell(2).setCellValue(new HSSFRichTextString("模具存放地点"));
                sheet.getRow(1).getCell(3).setCellValue(new HSSFRichTextString("模具类型"));
                sheet.getRow(1).getCell(4).setCellValue(new HSSFRichTextString("模具总数 （已报废不算入总数内）"));
                sheet.getRow(1).getCell(5).setCellValue(new HSSFRichTextString("各模具资产状态在位数量情况"));
                sheet.getRow(1).getCell(5 + asset_states.size()).setCellValue(new HSSFRichTextString("模具开模费（元）"));
                sheet.getRow(1).getCell(6 + asset_states.size()).setCellValue(new HSSFRichTextString("已摊销数量"));
                sheet.getRow(1).getCell(7 + asset_states.size()).setCellValue(new HSSFRichTextString("未摊销数量"));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 4 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 5 + asset_states.size(), 5 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 6 + asset_states.size(), 6 + asset_states.size()));
                sheet.addMergedRegion(new CellRangeAddress(1, 2, 7 + asset_states.size(), 7 + asset_states.size()));
            }
            if (i == 2) {
                for (int a = 0; a < asset_states.size(); a++) {
                    sheet.getRow(2).getCell(5 + a).setCellValue(new HSSFRichTextString(asset_states.get(a).getfLable()));
                }
            }
        }
        for (aOrg aOrg : divisionList) {
            List<aOrg> a = new ArrayList<>();
            dg(a, aOrgList, aOrg.getfId());
            row += a.size() * cast_type.size() * prod_spec.size();
            locationList.add(a);
        }
        int i = 3;
        int ii = 3;
        int hjsz = 0;
        int hjlc = 0;
        int hjbf = 0;
        Double hjfy = 0.0;
        int hjytx = 0;
        int hjwtx = 0;
        for (int j = 0; j < locationList.size(); j++) {
            int xjsz = 0;
            int xjlc = 0;
            int xjbf = 0;
            Double xjfy = 0.0;
            int xjytx = 0;
            int xjwtx = 0;
            for (int k = 0; k < locationList.get(j).size(); k++) {
                for (aDict castType : cast_type) {
                    if (locationList.get(j).get(k).getfName().indexOf("电轮") == -1 && castType.getfValue().equals("2")) {
                        continue;
                    }
                    sheet.createRow(i);
                    sheet.getRow(i).setHeight((short) 600);
                    for (int c = 0; c <= cell; c++) {
                        sheet.getRow(i).createCell(c).setCellStyle(style);
                    }
                    sheet.getRow(i).getCell(0).setCellValue(ii - 2);
                    sheet.getRow(i).getCell(1).setCellValue(new HSSFRichTextString(divisionList.get(j).getfName()));
                    sheet.getRow(i).getCell(2).setCellValue(new HSSFRichTextString(locationList.get(j).get(k).getfName()));
                    sheet.getRow(i).getCell(3).setCellValue(new HSSFRichTextString(castType.getfLable()));

                    int shizhi = 0;
                    int liangchang = 0;
                    int baofei = 0;
                    int yitanxiao = 0;
                    int weitanxiao = 0;
                    int zongshu = 0;
                    Double feiyong = 0.00;
                    for (Map<String, Object> map : allList) {
                        if (map.get("castTypeId").equals(castType.getfValue()) && map.get("location").equals(locationList.get(j).get(k).getfId())) {
                            if ((Boolean) map.get("amortization")) {
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
                            }
                            if ("1".equals(map.get("assetsState"))) {
                                shizhi += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                            } else if ("2".equals(map.get("assetsState"))) {
                                liangchang += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                            } else if ("3".equals(map.get("assetsState"))) {
                                baofei += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                            }
                            feiyong += Double.valueOf(StringUtils.isNotNull(map.get("wCost")) ? map.get("wCost").toString() : "0") * Double.valueOf(map.get("mainNums").toString());
                            if ((Boolean) map.get("amortization")) {
                                yitanxiao += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                            } else {
                                weitanxiao += Integer.parseInt(StringUtils.isNotNull(map.get("mainNums")) ? map.get("mainNums").toString() : "0");
                            }
                        }
                    }
                    xjbf += baofei;
                    xjlc += liangchang;
                    xjsz += shizhi;
                    xjfy += feiyong;
                    xjytx += yitanxiao;
                    xjwtx += weitanxiao;
                    hjbf += baofei;
                    hjlc += liangchang;
                    hjsz += shizhi;
                    hjfy += feiyong;
                    hjytx += yitanxiao;
                    hjwtx += weitanxiao;
                    sheet.getRow(i).getCell(4).setCellValue(shizhi + liangchang + baofei);
                    sheet.getRow(i).getCell(5).setCellValue(shizhi);
                    sheet.getRow(i).getCell(6).setCellValue(liangchang);
                    sheet.getRow(i).getCell(7).setCellValue(baofei);
                    sheet.getRow(i).getCell(5 + asset_states.size()).setCellValue(feiyong);
                    sheet.getRow(i).getCell(6 + asset_states.size()).setCellValue(yitanxiao);
                    sheet.getRow(i).getCell(7 + asset_states.size()).setCellValue(weitanxiao);
                    i++;
                    ii++;
                }
            }
            sheet.createRow(i);
            for (int c = 0; c <= cell; c++) {
                sheet.getRow(i).createCell(c).setCellStyle(style1);
            }
            sheet.getRow(i).getCell(4).setCellValue(xjbf + xjlc + xjsz);
            sheet.getRow(i).getCell(5).setCellValue(xjsz);
            sheet.getRow(i).getCell(6).setCellValue(xjlc);
            sheet.getRow(i).getCell(7).setCellValue(xjbf);
            sheet.getRow(i).getCell(5 + asset_states.size()).setCellValue(xjfy);
            sheet.getRow(i).getCell(6 + asset_states.size()).setCellValue(xjytx);
            sheet.getRow(i).getCell(7 + asset_states.size()).setCellValue(xjwtx);
            sheet.getRow(i).getCell(0).setCellValue(new HSSFRichTextString("小计"));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
            i++;
        }
        sheet.createRow(i);
        for (int c = 0; c <= cell; c++) {
            sheet.getRow(i).createCell(c).setCellStyle(style1);
        }
        sheet.getRow(i).getCell(4).setCellValue(hjbf + hjlc + hjsz);
        sheet.getRow(i).getCell(5).setCellValue(hjsz);
        sheet.getRow(i).getCell(6).setCellValue(hjlc);
        sheet.getRow(i).getCell(7).setCellValue(hjbf);
        sheet.getRow(i).getCell(5 + asset_states.size()).setCellValue(hjfy);
        sheet.getRow(i).getCell(6 + asset_states.size()).setCellValue(hjytx);
        sheet.getRow(i).getCell(7 + asset_states.size()).setCellValue(hjwtx);
        sheet.getRow(i).getCell(0).setCellValue(new HSSFRichTextString("合计"));
        sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));
        i++;
    }
}

