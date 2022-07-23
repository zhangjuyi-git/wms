package com.atguigu.wms.base.controller;
import com.atguigu.wms.vo.base.ShipperInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.atguigu.wms.base.service.ShipperInfoService;
import com.atguigu.wms.common.result.Result;
import com.atguigu.wms.model.base.ShipperInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @Author:juyi
 * @Date:2022/7/22 13:10
 */

@Api(tags = "shipperInfo管理")
@RestController
@RequestMapping(value = "/admin/base/shipperInfo")
public class ShipperInfoController {

    @Autowired
    ShipperInfoService shipperInfoService;

    @ApiOperation(value = "分页查询货主数据")
    @GetMapping("/{page}/{limit}")
    public Result shipperInfo(@PathVariable("page") Integer page,
                              @PathVariable("limit") Integer limit,
                              ShipperInfoQueryVo shipperInfoQueryVo) {
        IPage<ShipperInfo> iPage = new Page(page,limit);
        IPage<ShipperInfo> result = shipperInfoService.shipperInfo(iPage,shipperInfoQueryVo);

        return Result.ok(result);
    }


    @ApiOperation(value = "添加货主信息")
    @PostMapping("/save")
    public Result save(@RequestBody ShipperInfo shipperInfo) {
        boolean save = shipperInfoService.save(shipperInfo);
        return Result.ok(save);
    }

    ///get/1

    @ApiOperation(value = "回显货主信息")
    @GetMapping("/get/{shipperInfoId}")
    public Result getShipperInfoById(@PathVariable("shipperInfoId")Long shipperInfoId){

        ShipperInfo data = shipperInfoService.getShipperInfoById(shipperInfoId);
        return Result.ok(data);
    }


    @ApiOperation(value = "修改货主信息")
    @PutMapping("/update")
    public Result updateShipperInfo(@RequestBody ShipperInfo shipperInfo){

        shipperInfoService.updateShipperInfo(shipperInfo);
        return Result.ok();
    }


    @ApiOperation(value = "删除货主信息")
    @DeleteMapping("/remove/{shipperInfoId}")
    public Result removeShipperInfoById(@PathVariable("shipperInfoId")Long shipperInfoId){

        shipperInfoService.removeShipperInfoById(shipperInfoId);
        return Result.ok();
    }

}
