package com.yc.wowo.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.MemberInfo;
import com.yc.wowo.biz.IMemberInfoBiz;
import com.yc.wowo.mapper.IMemberInfoMapper;
import com.yc.wowo.util.StringUtil;


@Service
public class MemberInfoBizImpl implements IMemberInfoBiz {
	@Autowired
	private IMemberInfoMapper memberInfoMapper;
	
	@Override
	public int add(MemberInfo mf) {
		if (StringUtil.checkNull(mf.getNickName(), mf.getPwd(), mf.getEmail())) {
			return -1;
		}
		return memberInfoMapper.add(mf);
	}

	@Override
	public MemberInfo login(Map<String, Object> map) {
		if (StringUtil.checkNull(String.valueOf(map.get("account")), String.valueOf(map.get("pwd")))) {
			return null;
		}
		return memberInfoMapper.login(map);
	}

	@Override
	public MemberInfo finds(String nickName) {
		return memberInfoMapper.finds(nickName);
	}

	
}
