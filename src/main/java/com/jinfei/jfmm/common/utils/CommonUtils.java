package com.jinfei.jfmm.common.utils;

import com.jinfei.jfmm.model.*;
import com.jinfei.jfmm.services.*;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommonUtils {

    //部门接口
    @Resource IAOrgService iaOrgService;

    //字典接口
    @Resource
    IADictService iaDictService;

    //模具型号接口
    @Resource IMModelService imModelService;

    //供应商接口
    @Resource IASuppService iaSuppService;

    //客户接口
    @Resource
    IACustService iaCustService;

    //职员接口
    @Resource
    IStaffService iStaffService;

    //部件接口
    @Resource
    IMainService iMainService;

    //模具编号接口
    @Resource IMMainService imMainService;

    //部件型号接口
    @Resource IBModelService ibModelService;

    //库位接口
    @Resource IKSeatService ikSeatService;

    //用户接口
    @Resource IUserService iUserService;

    private static CommonUtils commonUtils;

    @PostConstruct
    public void init(){
        commonUtils = this;
        commonUtils.iaOrgService = this.iaOrgService;
        commonUtils.iaDictService = this.iaDictService;
        commonUtils.imModelService = this.imModelService;
        commonUtils.iStaffService = this.iStaffService;
        commonUtils.iMainService = this.iMainService;
        commonUtils.ibModelService = this.ibModelService;
        commonUtils.iaSuppService = this.iaSuppService;
        commonUtils.iaCustService = this.iaCustService;
        commonUtils.imMainService = this.imMainService;
        commonUtils.ikSeatService = this.ikSeatService;
        commonUtils.iUserService = this.iUserService;
    }

    /**
     * 获取库位集合
     * @return
     */
    public static List<kSeat> getKSeat(){
        return commonUtils.ikSeatService.list();
    }

    /**
     * 获取组织机构集合
     * @return
     */
    public static List<aOrg> getAOrg(){
        aOrg org = new aOrg();
        org.setIsWh((byte) 2);
        return commonUtils.iaOrgService.list(org);
    }

    /**
     * 根据ID返回组织机构名称
     * @param fId
     * @return
     */
    public static String getfName(String fId){
        try {
            aOrg aOrg = commonUtils.iaOrgService.selectByPrimaryKey(fId);
            return aOrg.getfName();
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 根据ID返回组织机构名称
     *
     * @return
     */
    public static List<aOrg> ListOrg(){
            return commonUtils.iaOrgService.list(new aOrg());
    }

    /**
     * 获取字典类型option
     * @return
     */
    public static String getDictOption(){
        List<aDict> aDicts = commonUtils.iaDictService.getDictType();
        String option = "";
        for (com.jinfei.jfmm.model.aDict aDict:aDicts){
            option+="<option value = '"+aDict.getfId()+"'>"+aDict.getfLable()+"</option>";
        }
        return option;
    }

    /**
     * 获取字典类型option
     * @return
     */
    public static List<aDict> getDictObj(String type){
        List<aDict> aDicts = commonUtils.iaDictService.getDictOption(type);
        return aDicts;
    }

    /**
     * 获取职员信息option
     * @return
     */
    public static String getStaffOption(){
        List<Staff> staffs = commonUtils.iStaffService.selectAll(new Staff());
        String option = "";
        for (Staff staff:staffs){
            option+="<option value = '"+staff.getF_id()+"'>"+staff.getF_name()+"</option>";
        }
        return option;
    }

    /**
     * 获取部件信息option
     * @return
     */
    public static String getPartOption(){
        List<Main> Mains = commonUtils.iMainService.selectAll(new Main());
        String option = "";
        for (Main Main:Mains){
            option+="<option value = '"+Main.getF_id()+"'>"+Main.getF_name()+"</option>";
        }
        return option;
    }

    /**
     * 获取部件型号信息option
     * @return
     */
    public static String getBModelOption(){
        List<BModel> bModels = commonUtils.ibModelService.selectAll(new BModel());
        String option = "";
        for (BModel bModel:bModels){
            option+="<option value = '"+bModel.getF_id()+"'>"+bModel.getF_name()+"</option>";
        }
        return option;
    }

    public static List<BModel> getBModelAll(){
        List<BModel> bModels = commonUtils.ibModelService.selectAll(new BModel());
        return bModels;
    }

    /**
     * 获取字典相应类型option
     * @return
     */
    public static String getDictTypeOption(String fValue){
        List<aDict> aDicts = commonUtils.iaDictService.getDictOption(fValue);
        String option = "";
        for (com.jinfei.jfmm.model.aDict aDict:aDicts){
            option+="<option value = '"+aDict.getfValue()+"'>"+aDict.getfLable()+"</option>";
        }
        return option;
    }

    /**
     * 获取部件类型复选框
     * @return
     */
    public static String getPartsTypeCheckBox(){
        List<aDict> aDicts = commonUtils.iaDictService.getDictOption("parts_cate");
        String CheckBox = "";
        for (com.jinfei.jfmm.model.aDict aDict:aDicts){
            CheckBox+="<label class=\"btn btn-default active\"><input type=\"checkbox\"  checked ='checked' value = '"+aDict.getfValue()+"'>"+aDict.getfLable()+"</label>";
        }
        return CheckBox;
    }

    /**
     * 获取模具编号
     * @return
     */
    public static String getMainListOption(){
        List<mMain> mMains = commonUtils.imMainService.list(new mMain());
        String option = "";
        for (mMain mMain:mMains){
            option+="<option value = '"+mMain.getfId()+"'>"+mMain.getfName()+"-"+mMain.getProdClassifyName()+"-"+mMain.getSpecName()+"</option>";
        }
        System.out.println(option);
        return option;
    }

    /**
     * 获取模具型号option
     * @return
     */
    public static String getModelListOption(){
        List<mModel> mModels = commonUtils.imModelService.list(new mModel());
        String option = "";
        for (mModel mModel:mModels){
            option+="<option value = '"+mModel.getfId()+"'>"+mModel.getfName()+"-"+mModel.getProdClassifyName()+"-"+mModel.getSpecName()+"</option>";
        }
        System.out.println(option);
        return option;
    }

    /**
     * 根据模具型号id返回模具名称
     * @param fId
     * @return
     */
    public static String getMouldName(String fId){
        mModel mModel = (mModel) commonUtils.imModelService.getModel(fId);
        return mModel.getfName();
    }

    public static String getSuppListOption(){
        List<aSupp> aSupps = commonUtils.iaSuppService.list(new aSupp());
        String option = "";
        for (aSupp aSupp:aSupps){
            option+="<option value = '"+aSupp.getfId()+"'>"+aSupp.getfName()+"</option>";
        }
        return option;
    }

    public static String getCustListOption(){
        List<aCust> aCusts = commonUtils.iaCustService.list(new aCust());
        String option = "";
        for (aCust aCust:aCusts){
            option+="<option value = '"+aCust.getfId()+"'>"+aCust.getfName()+"</option>";
        }
        return option;
    }

    /**
     * 获取用户列表
     */
    public static List<User> getUserList(){
     return commonUtils.iUserService.selectUserList(new User());
    }

}
