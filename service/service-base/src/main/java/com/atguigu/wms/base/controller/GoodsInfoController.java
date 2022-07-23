package com.atguigu.wms.base.controller;

import com.atguigu.wms.base.service.GoodsTypeService;
import com.atguigu.wms.model.base.GoodsInfo;
import com.atguigu.wms.model.base.GoodsType;
import com.atguigu.wms.model.base.ShipperInfo;
import com.atguigu.wms.model.inventory.InventoryInfo;
import com.atguigu.wms.vo.PageVo;
import com.atguigu.wms.vo.base.GoodsInfoQueryVo;
import com.atguigu.wms.base.service.GoodsInfoService;
import com.atguigu.wms.vo.base.ShipperInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.atguigu.wms.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author qy
 *
 */
@Api(value = "GoodsInfo管理", tags = "GoodsInfo管理")
@RestController
@RequestMapping(value="/admin/base/goodsInfo")
@SuppressWarnings({"unchecked", "rawtypes"})
public class GoodsInfoController {
	
	@Resource
	private GoodsInfoService goodsInfoService;
	@Autowired
	GoodsTypeService goodsTypeService;


	@ApiOperation(value = "根据关键字查看")
	@GetMapping("findByKeyword/{keyword}")
	public Result findByKeyword(@PathVariable String keyword) {
		return Result.ok(goodsInfoService.findByKeyword(keyword));
	}

	@ApiOperation(value = "根据第三级分类id获取货品三级分类id列表")
	@GetMapping("findGoodsTypeIdList/{goodsTypeId}")
	public Result findGoodsTypeIdList(@PathVariable Long goodsTypeId) {
		return Result.ok(goodsInfoService.findGoodsTypeIdList(goodsTypeId));
	}

	@ApiOperation(value = "获取列表")
	@PostMapping("findList")
	public List<GoodsInfo> findList(@RequestBody List<Long> idList) {
		return goodsInfoService.listByIds(idList);
	}


	@ApiOperation(value = "获取分页列表")
	@PostMapping("findPage/{page}/{limit}")
	public PageVo<GoodsInfo> findPage(
			@ApiParam(name = "page", value = "当前页码", required = true)
			@PathVariable Long page,

			@ApiParam(name = "limit", value = "每页记录数", required = true)
			@PathVariable Long limit,

			@ApiParam(name = "goodsInfoVo", value = "查询对象", required = false)
					@RequestBody GoodsInfoQueryVo goodsInfoQueryVo) {
		Page<GoodsInfo> pageParam = new Page<>(page, limit);
		//IPage<GoodsInfo> pageModel = goodsInfoService.selectPage(pageParam, goodsInfoQueryVo);
		return new PageVo<>(null);
	}

	@ApiOperation(value = "获取")
	@GetMapping("getGoodsInfo/{id}")
	public GoodsInfo getGoodsInfo(@PathVariable Long id) {
		return goodsInfoService.getGoodsInfo(id);
	}

	@ApiOperation(value = "获取")
	@GetMapping("getGoodsInfoBySkuId/{skuId}")
	public GoodsInfo getGoodsInfoBySkuId(@PathVariable Long skuId) {
		return goodsInfoService.getGoodsInfoBySkuId(skuId);
	}

	@ApiOperation(value = "分页查询货主数据")
	@GetMapping("/{page}/{limit}")
	public Result goodsInfo(@PathVariable("page") Integer page,
							  @PathVariable("limit") Integer limit,
							GoodsInfoQueryVo goodsInfoQueryVo) {
		IPage<GoodsInfo> iPage = new Page(page,limit);
		IPage<GoodsInfo> result = goodsInfoService.goodsInfo(iPage,goodsInfoQueryVo);

		return Result.ok(result);
	}

	@PostMapping("/save")
	public Result saveGoodsInfo(@RequestBody GoodsInfo goodsInfo){
		goodsInfoService.save(goodsInfo);
		return Result.ok();
	}


	@ApiOperation(value = "启用/下线")
	@GetMapping("/updateStatus/{id}/{status}")
	public Result updateStatus(@PathVariable("id") Long id,
							   @PathVariable("status")Integer status) {
		goodsInfoService.updateStatus(id,status);
		return Result.ok();
	}

	@GetMapping("/get/{id}")
	public Result getGoodsInfoById(@PathVariable("id") Long id) {
		GoodsInfo byId = goodsInfoService.getById(id);
		return Result.ok(byId);
	}

	@PutMapping("/update")
	public Result updateGoodsInfo(@RequestBody GoodsInfo goodsInfo) {
		goodsInfoService.updateById(goodsInfo);
		return Result.ok();
	}

	//remove/39
	@DeleteMapping("/remove/{id}")
	public Result removeGoodsInfo(@PathVariable("id")Long id) {
		goodsInfoService.removeById(id);
		return Result.ok();
	}

}

