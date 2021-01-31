package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.GoodsInfo;

public interface IGoodsInfoMapper {
	
	public List<GoodsInfo> findByPage(Map<String, Object>map);
	
	public int total(boolean flag);
	
	public int add(GoodsInfo gf);
	
	public int update(GoodsInfo gf);
	
	public List<GoodsInfo> finds(Map<String, Object> map);
	
	public GoodsInfo findByGid(String gid);
	
	public int totals(Map<String, Object>map);
	
	public List<GoodsInfo> findByCondition(Map<String, Object>map);

}
