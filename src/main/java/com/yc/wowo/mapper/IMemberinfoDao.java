package com.yc.wowo.mapper;




import java.util.List;

import com.yc.wowo.bean.MemberInfo;


public interface IMemberinfoDao {
	public List<MemberInfo> findByPage(int page, int rows);
	
	public int total();
	
	public List<MemberInfo> findAll();
	
	public int add(MemberInfo  me);
	
	public int update(MemberInfo  me);
	
	public int updates(MemberInfo  me);
	
	public int updatepwd(String pwd ,String nickName);
	
	
	public MemberInfo finds(String nickName);
	
	public MemberInfo findByMid(Integer Mid);
	
	public MemberInfo login(String aname, String pwd);
}
