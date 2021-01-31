package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.ShopInfo;
import com.yc.wowo.dto.JsonObject;

public interface IShopInfoBiz {
	public JsonObject findByPage(Map<String, Object> map);
	//public int total();
	/**
	 * 添加店铺信息
	 */
	public int add(ShopInfo sp);
	
	/**
	 * 修改店铺信息
	 */
	public int update(ShopInfo sp);
	
	/**
	 * 查询�?有正常营业的店铺编号和名�?
	 */
	public List<ShopInfo> finds();
	
	
	public ShopInfo findBySid(String sid); 
	
	public JsonObject findByCondition(Map<String, Object> map);

	
}
