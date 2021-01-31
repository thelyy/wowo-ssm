package com.yc.wowo.util;
/**
 * @author Administrator
 *
 */

public class StringUtil {
	/**
	 * 空判�?
	 * @author Administrator
	 *
	 */
	public static boolean checkNull(String ...strs) {
		if (strs == null || strs.length <= 0) {
			return true;
		}
		
		for (String str : strs) {
			if (str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}

}
