package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.TypeInfo;
import com.yc.wowo.biz.ITypeInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.mapper.ITypeInfoMapper;
import com.yc.wowo.util.StringUtil;



@Service
public class TypeInfoBizImpl implements ITypeInfoBiz{
	@Autowired
	private ITypeInfoMapper typeInfoMapper;
	
	
	@Override
	public int add(TypeInfo tf) {
		if(StringUtil.checkNull(tf.getTname())) {
		return -1;
	}
		return typeInfoMapper.add(tf);
	}

	@Override
	public int update(TypeInfo tf) {
		if(StringUtil.checkNull(tf.getTname())) {
			return -1;
		}
		return typeInfoMapper.update(tf);
	}

	@Override
	public List<TypeInfo> findAll() {
		return typeInfoMapper.findAll();
	}
	
	@Override
	public List<TypeInfo> finds() {
		return typeInfoMapper.finds();
	}

	@Override
	public JsonObject findByPage(Map<String, Object> map) {
		return new JsonObject(typeInfoMapper.total(), typeInfoMapper.findByPage(map));
	}


	

	
}
