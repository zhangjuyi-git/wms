package com.atguigu.wms.base.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.atguigu.wms.base.service.DictService;
import com.atguigu.wms.base.mapper.DictMapper;
import com.atguigu.wms.common.excel.ExcelHelper;
import com.atguigu.wms.model.base.Dict;
import com.atguigu.wms.model.base.DictData;
import com.atguigu.wms.vo.base.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

	@Autowired
	private DictMapper dictMapper;

	/**
	 * 根据上级id获取子节点数据列表
	 * allEntries = true: 方法调用后清空所有缓存
	 * beforeInvocation = true：方法调用前清空所有缓存
	 * @param parentId
	 */


	/**
	 * 导入
	 * allEntries = true: 方法调用后清空所有缓存
	 * beforeInvocation = true：方法调用前清空所有缓存
	 * @param file
	 */
	//@CacheEvict(value = "dict", allEntries=true)


	@Cacheable(value = "dict",keyGenerator = "keyGenerator")
	@Override
	public String getNameById(Long id) {
		Dict dict = this.getById(id);
		if(null == dict) return "";
		return dict.getName();
	}

    @Override
    public List<Dict> findDictByParentId(Long parentId) {

		QueryWrapper<Dict> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id",parentId);
		List<Dict> data = dictMapper.selectList(wrapper);
		for (Dict datum : data) {
			Long id = datum.getId();
			QueryWrapper<Dict> childWrapper = new QueryWrapper<>();
			childWrapper.eq("parent_id",id);
			Integer integer = dictMapper.selectCount(childWrapper);
			if (integer > 0){
				datum.setHasChildren(true);
			}
		}
		return data;
	}

	@Override
	public void exportData(HttpServletResponse response) throws IOException {
		// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
		String fileName = URLEncoder.encode("数据字典", "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("模板").doWrite(data());
	}

    @Override
    public List<Dict> getInspectType(String inspectType) {
		QueryWrapper<Dict> wrapper = new QueryWrapper<>();
		wrapper.eq("dict_code",inspectType);
		Dict dict = dictMapper.selectOne(wrapper);
		Long id = dict.getId();

		QueryWrapper<Dict> childWrapper = new QueryWrapper<>();
		childWrapper.eq("parent_id",id);
		List<Dict> list = dictMapper.selectList(childWrapper);
		return list;
    }

    private List<DictEeVo> data() {
		List<DictEeVo> list = new ArrayList();
		List<Dict> list1 = this.list();
		for (Dict dict : list1) {
			DictEeVo data = new DictEeVo();
			data.setId(dict.getId());
			data.setParentId(dict.getParentId());
			data.setName(dict.getName());
			data.setDictCode(dict.getDictCode());
			list.add(data);
		}
		return list;
	}



//	@Cacheable(value = "dict",keyGenerator = "keyGenerator")
//	@Override
//	public String getNameByParentDictCodeAndValue(String parentDictCode) {
//		//如果value能唯一定位数据字典，parentDictCode可以传空，例如：省市区的value值能够唯一确定
//		if(StringUtils.isEmpty(parentDictCode)) {
//			Dict dict = dictMapper.selectOne(new QueryWrapper<Dict>().eq("value", value));
//			if(null != dict) {
//				return dict.getName();
//			}
//		} else {
//			Dict parentDict = this.getByDictsCode(parentDictCode);
//			if(null == parentDict) return "";
//			Dict dict = dictMapper.selectOne(new QueryWrapper<Dict>().eq("parent_id", parentDict.getId()).eq("value", value));
//			if(null != dict) {
//				return dict.getName();
//			}
//		}
//		return "";
//	}

//	/**
//	 * https://alibaba-easyexcel.github.io/quickstart/read.html
//	 * @param file
//	 */
//	@Override
//	public void importData(MultipartFile file) {
//		try {
//			EasyExcel.read(file.getInputStream(), DictEeVo.class, new DictDataListener(dictMapper)).sheet().doRead();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * https://alibaba-easyexcel.github.io/quickstart/write.html
//	 * @param response
//	 */
//	@Override
//	public void exportData(HttpServletResponse response) {
//		try {
//			response.setContentType("application/vnd.ms-excel");
//			response.setCharacterEncoding("utf-8");
//			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//			String fileName = URLEncoder.encode("数据字典", "UTF-8");
//			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//
//			List<Dict> dictList = dictMapper.selectList(null);
//			List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
//			for(Dict dict : dictList) {
//				DictEeVo dictVo = new DictEeVo();
//				BeanUtils.copyBean(dict, dictVo, DictEeVo.class);
//				dictVoList.add(dictVo);
//			}
//
//			EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典").doWrite(dictVoList);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}


}
