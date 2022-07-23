package com.atguigu.wms.base.service;


import com.atguigu.wms.model.base.Dict;
import com.atguigu.wms.model.base.DictData;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;;import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface DictService extends IService<Dict> {

    String getNameById(Long id);

    List<Dict> findDictByParentId(Long parentId);


    void exportData(HttpServletResponse response) throws IOException;


    List<Dict> getInspectType(String inspectType);


    /**
     * 根据上级编码与值获取数据字典名称
     * @param parentDictCode
     * @param value
     * @return
     */
    //String getNameByParentDictCodeAndValue(String parentDictCode, String value);

}
