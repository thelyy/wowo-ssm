package com.yc.wowo.util;

import java.util.Map;




public class RequestParamUtil {
	
	public static Map<String, Object> findByPageUtil(Map<String, Object> map) {
		int page = Integer.parseInt(String.valueOf(map.get("page")));
		int rows = Integer.parseInt(String.valueOf(map.get("rows")));
		
		map.put("page", (page - 1) * rows);
		map.put("rows", rows);
		return map;
	}

}
