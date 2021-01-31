package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.GoodsInfo;
import com.yc.wowo.dto.JsonObject;

public interface IGoodsInfoBiz {
	
	//public int GoodID();
	
	public int add(GoodsInfo gf);
	
	/**
	 * 修改店铺信息
	 */
	public int update(GoodsInfo gf);
	
	/**
	 * 查询�?有正常营业的店铺编号和名�?
	 */
	public GoodsInfo findByGid(String gid); 
	
	
	public JsonObject findByPage(Map<String, Object> map);
	
	public List<GoodsInfo> finds(Map<String, Object> map);
	
	public int total();
	
	public JsonObject findByCondition(Map<String, Object> map);

	

	
}
