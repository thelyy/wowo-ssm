package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.TypeInfo;
import com.yc.wowo.dto.JsonObject;



public interface ITypeInfoBiz {
	/**
	 * 添加商品类型信息
	 * @author Administrator
	 *
	 */

	public int add(TypeInfo tf);
	/**
	 * 修改商品类型信息
	 * @author Administrator
	 *
	 */

	public int update(TypeInfo tf);
	/**
	 * 查询�?有商品类型信�?
	 * @author Administrator
	 *
	 */

	public List<TypeInfo> findAll();
	/**
	 * 获取未下架的商品类型信息
	 * @author Administrator
	 *
	 */
	
	public List<TypeInfo> finds();
	/**
	 * 分页查询
	 * @param page 查询第几�?
	 * @param rows 每页显示多少�?
	 * @author Administrator
	 *
	 */

	public JsonObject findByPage(Map<String, Object> map);
	
}
