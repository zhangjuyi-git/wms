package com.atguigu.wms.base.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.atguigu.wms.common.result.Result;
import com.atguigu.wms.base.service.DictService;
import com.atguigu.wms.model.base.Dict;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author qy
 */
//@CrossOrigin //跨域
@Api(tags = "数据字典管理")
@RestController
@RequestMapping(value = "/admin/base/dict")
public class DictController   {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据id获取名称")
    @GetMapping("getNameById/{id}")
    public String importData(@PathVariable Long id) {
        return dictService.getNameById(id);
    }

//    @ApiOperation(value = "获取数据字典名称")
//    @GetMapping(value = "/getName/{parentDictCode}/{value}")
//    public String getName(
//            @ApiParam(name = "parentDictCode", value = "上级编码", required = true)
//            @PathVariable("parentDictCode") String parentDictCode,
//
//            @ApiParam(name = "value", value = "值", required = true)
//            @PathVariable("value") String value) {
//        return dictService.getNameByParentDictCodeAndValue(parentDictCode, value);
//    }

//    @ApiOperation(value = "获取数据字典名称")
//    @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "Long", paramType = "path")
//    @GetMapping(value = "/getName/{value}")
//    public String getName(
//            @ApiParam(name = "value", value = "值", required = true)
//            @PathVariable("value") String value) {
//        return dictService.getNameByParentDictCodeAndValue("", value);
//    }

    @GetMapping("/findByParentId/{parentId}")
    public Result findDictByParentId(@PathVariable("parentId")Long parentId){
        List<Dict> data =dictService.findDictByParentId(parentId);
    return Result.ok(data);
    }

    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) throws IOException {
        dictService.exportData(response);
    }


    @GetMapping("/findByDictCode/{inspectType}")
    public Result getInspectType(@PathVariable("inspectType")String inspectType)  {
        List<Dict> list = dictService.getInspectType(inspectType);
        return Result.ok(list);
    }

}

