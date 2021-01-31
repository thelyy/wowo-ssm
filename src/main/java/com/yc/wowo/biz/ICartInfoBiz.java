package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.CartInfo;

public interface ICartInfoBiz {
	public int add(Map<String, Object> map);
	
	public List<CartInfo> findByMid(Integer mid);
	
//	public CartInfo findMid(Integer mid);
	
//	public int updateNum(String gid,Integer mid);
//	
//	public int updateStatus(Integer gid,Integer mid);
	
	public int update(Map<String, Object> map);
    
	public int del(String cid);
	
	//public JsonObject findByPage(int page, int rows);
}
