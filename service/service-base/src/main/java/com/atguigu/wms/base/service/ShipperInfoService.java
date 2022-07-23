package com.atguigu.wms.base.service;

import com.atguigu.wms.model.base.ShipperInfo;
import com.atguigu.wms.vo.base.ShipperInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ShipperInfoService extends IService<ShipperInfo> {

    IPage<ShipperInfo> shipperInfo(IPage<ShipperInfo> page1, ShipperInfoQueryVo shipperInfoQueryVo);

    ShipperInfo getShipperInfoById(Long shipperInfoId);

    void updateShipperInfo(ShipperInfo shipperInfo);

    void removeShipperInfoById(Long shipperInfoId);
}
