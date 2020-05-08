package com.jinfei.jfmm.common.web.service;

import com.jinfei.jfmm.model.aDict;
import com.jinfei.jfmm.model.aOrg;
import com.jinfei.jfmm.services.IADictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * html调用 thymeleaf 实现字典读取
 * 
 * @author numberone
 */
@Service("dict")
public class DictService
{
    @Autowired
    private IADictService iaDictService;

    /**
     * 根据字典类型查询字典数据信息
     * 
     * @param type 字典类型
     * @eturn 参数键值
     */
    public List<aDict> getType(String type)
    {
        return iaDictService.getDictOption(type);
    }

    public List<aDict> getDictType()
    {
        return iaDictService.getDictType();
    }

    /**
     * 获取部件类型复选框
     * @return
     */
    public String getPartsTypeCheckBox(){
        List<aDict> aDicts = iaDictService.getDictOption("parts_cate");
        String CheckBox = "";
        for (com.jinfei.jfmm.model.aDict aDict:aDicts){
            CheckBox+="<label class=\"btn btn-default active\"><input type=\"checkbox\"  checked ='checked' value = '"+aDict.getfValue()+"'>"+aDict.getfLable()+"</label>";
        }
        return CheckBox;
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
//    public String getLabel(String dictType, String dictValue)
//    {
//        return iaDictService.selectDictLabel(dictType, dictValue);
//    }
}
