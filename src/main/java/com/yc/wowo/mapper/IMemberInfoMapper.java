package com.yc.wowo.mapper;




import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.MemberInfo;


public interface IMemberInfoMapper {
	public List<MemberInfo> findByPage(int page, int rows);
	
	public int total();
	
	public List<MemberInfo> findAll();
	
	public int add(MemberInfo  mf);
	
	public MemberInfo login(Map<String, Object> map);
	
	public int update(MemberInfo  mf);
	
	public int updates(MemberInfo  mf);
	
	public int updatepwd(String pwd ,String nickName);
	
	public MemberInfo finds(String nickName);
	
	public MemberInfo findByMid(Integer Mid);
	
}
