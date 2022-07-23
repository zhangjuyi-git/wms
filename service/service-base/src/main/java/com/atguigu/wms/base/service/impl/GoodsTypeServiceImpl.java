package com.atguigu.wms.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.wms.model.base.Dict;
import com.atguigu.wms.model.base.GoodsType;
import com.atguigu.wms.model.base.StoreshelfInfo;
import com.atguigu.wms.vo.base.GoodsTypeQueryVo;
import com.atguigu.wms.base.mapper.GoodsTypeMapper;
import com.atguigu.wms.base.service.GoodsTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements GoodsTypeService {

	@Autowired
	GoodsTypeMapper goodsTypeMapper;


	@Override
	public String getNameById(Long id) {
		GoodsType goodsType = this.getById(id);
		return goodsType.getName();
	}

    @Override
    public List<GoodsType> findByParentId(Long parentId) {

		QueryWrapper<GoodsType> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id",parentId);
		List<GoodsType> data = goodsTypeMapper.selectList(wrapper);
		for (GoodsType datum : data) {
			Long id = datum.getId();
			QueryWrapper<GoodsType> childWrapper = new QueryWrapper<>();
			childWrapper.eq("parent_id",id);
			Integer integer = goodsTypeMapper.selectCount(childWrapper);
			if (integer > 0){
				datum.setHasChildren(true);
			}
		}
		return data;
	}

    @Override
    public List<GoodsType> findNodes() {
		QueryWrapper<GoodsType> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id",0L);
		List<GoodsType> list = list();
		for (GoodsType goodsType : list) {
			Long id = goodsType.getId();
			goodsType.setValue(id+"");
			goodsType.setLabel(goodsType.getName());
			List<GoodsType> childGoodsType = findByParentId(id);
			for (GoodsType type : childGoodsType) {
				Long id1 = type.getId();
				type.setValue(id1+"");
				type.setLabel(type.getName());
				List<GoodsType> byParentId = findByParentId(id1);
				for (GoodsType goodsType1 : byParentId) {
					goodsType1.setValue(goodsType1.getId()+"");
					goodsType1.setLabel(goodsType1.getName());
				}
				type.setChildren(byParentId);
			}
			goodsType.setChildren(childGoodsType);
		}
		return list;
    }

    @Override
    public List<GoodsType> findGoodsTypeIdList(Long parentId) {
		QueryWrapper<GoodsType> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id",parentId);
		List<GoodsType> goodsTypes = goodsTypeMapper.selectList(wrapper);
		return goodsTypes;
    }


}
