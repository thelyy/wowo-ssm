package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.TypeInfo;

public interface ITypeInfoMapper {
	public int add(TypeInfo tf);
	
	public int update(TypeInfo tf);
	
	public List<TypeInfo> findAll();
	
	public List<TypeInfo> finds();
	
	public int total();
	
	public List<TypeInfo> findByPage(Map<String, Object>map);
}
