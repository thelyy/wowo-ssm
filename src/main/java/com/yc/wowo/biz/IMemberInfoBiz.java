package com.yc.wowo.biz;


import java.util.Map;

import com.yc.wowo.bean.MemberInfo;

public interface IMemberInfoBiz {

	public int add(MemberInfo  mf);
	
	public MemberInfo login(Map<String, Object> map);
	
	public MemberInfo finds(String nickName);
		
		
}
