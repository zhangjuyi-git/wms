package com.atguigu.wms.base.service.impl;

import com.atguigu.wms.model.base.ShipperInfo;
import com.atguigu.wms.vo.base.ShipperInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.wms.base.service.ShipperInfoService;
import com.atguigu.wms.base.mapper.ShipperInfoMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 *
 */
@Service
public class ShipperInfoServiceImpl extends ServiceImpl<ShipperInfoMapper, ShipperInfo>
    implements ShipperInfoService{

    @Autowired
    ShipperInfoMapper shipperInfoMapper;
    @Override
    public IPage<ShipperInfo>  shipperInfo(IPage<ShipperInfo> listPage, ShipperInfoQueryVo shipperInfoQueryVo) {
        IPage<ShipperInfo> Page =shipperInfoMapper.shipperInfo(listPage,shipperInfoQueryVo);
        return Page;
    }

    @Override
    public ShipperInfo getShipperInfoById(Long shipperInfoId) {
        ShipperInfo data = shipperInfoMapper.selectById(shipperInfoId);
        return data;
    }

    @Override
    public void updateShipperInfo(ShipperInfo shipperInfo) {
        shipperInfoMapper.updateById(shipperInfo);
    }

    @Override
    public void removeShipperInfoById(Long shipperInfoId) {
        shipperInfoMapper.deleteById(shipperInfoId);
    }
}




