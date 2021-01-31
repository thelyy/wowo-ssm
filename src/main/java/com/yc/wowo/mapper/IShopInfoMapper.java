package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.ShopInfo;



public interface IShopInfoMapper {
	public List<ShopInfo> findByPage(Map<String, Object> map);
	
	public int total();
	/**
	 * 添加店铺信息
	 * @author Administrator
	 *
	 */
	public int add(ShopInfo sp);
	
	/**
	 * 修改店铺信息
	 * @author Administrator
	 *
	 */
	public int update(ShopInfo sp);
	
	/**
	 * 查询�?有正常营业的店铺的编号和名称
	 * @author Administrator
	 *
	 */
	
	public List<ShopInfo> finds();
	
	/**
	 * 根据店铺编号查询店铺详细
	 * @author Administrator
	 *
	 */
	public ShopInfo findBySid(String sid);
	
	
	public int totals(Map<String, Object> map);
	
	
	public List<ShopInfo> findByCondition(Map<String, Object> map);
	/**
	 * 多条件组合分页查�?
	 * @author Administrator
	 *
	 */
}
