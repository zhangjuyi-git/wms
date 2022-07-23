package com.atguigu.wms.base.mapper;

import com.atguigu.wms.model.base.ShipperInfo;
import com.atguigu.wms.vo.base.ShipperInfoQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.atguigu.wms.base.domain.ShipperInfo
 */
public interface ShipperInfoMapper extends BaseMapper<ShipperInfo> {

    IPage<ShipperInfo> shipperInfo(@Param("Page") IPage<ShipperInfo> IPage,ShipperInfoQueryVo shipperInfoQueryVo);
}




