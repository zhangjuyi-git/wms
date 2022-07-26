package com.atguigu.wms.base.controller;

import com.atguigu.wms.model.base.Dict;
import com.atguigu.wms.model.base.GoodsType;
import com.atguigu.wms.vo.base.GoodsTypeQueryVo;
import com.atguigu.wms.base.service.GoodsTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.atguigu.wms.common.result.Result;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author qy
 *
 */
@Api(value = "GoodsType管理", tags = "GoodsType管理")
@RestController
@RequestMapping(value="/admin/base/goodsType")
@SuppressWarnings({"unchecked", "rawtypes"})
public class GoodsTypeController {

    @Autowired
    GoodsTypeService goodsTypeService;


    @GetMapping("/findByParentId/{parentId}")
    public Result findByParentId(@PathVariable("parentId")Long parentId){
        List<GoodsType> data = goodsTypeService.findByParentId(parentId);
        return Result.ok(data);
    }

    @GetMapping("/findNodes")
    public Result findNodes(){
        List<GoodsType > data = goodsTypeService.findNodes();
        return Result.ok(data);
    }

}

