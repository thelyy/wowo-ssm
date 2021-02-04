package com.yc.wowo.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;





public class RequestParamUtil {
	
	public static Map<String, Object> findByPageUtil(Map<String, Object> map) {
		int page = Integer.parseInt(String.valueOf(map.get("page")));
		int rows = Integer.parseInt(String.valueOf(map.get("rows")));
		
		map.put("page", (page - 1) * rows);
		map.put("rows", rows);
		return map;
	}
	
	/**
	 * 将请求中的参数封装成对象返回
	 * 
	 */
	public static <T> T getParams(Class<T>cls, HttpServletRequest request) {
		T t = null;
		try {
			//获取给定的类中的承有setter方法
			Method[] methods = cls.getMethods();
			
			//存储承有是setter方法，以方法名为键，对应的方法对象为倿
			Map<String, Method>setters = new HashMap<String, Method>();
			
			String methodName = null;
			for(Method method : methods) {
				methodName = method.getName();
				if(methodName.startsWith("set")) {//说明当前方法是一个setter方法
					setters.put(methodName, method);
					
				}
			}
			//获取请求中的承有参数的参数吿
			Enumeration<String>names = request.getParameterNames();
			
			//循环承有的参数，找到这个参数注入时对应的setter方法＿ 将这个参数注入到对应的对象的属濧中
			String name = null;
			Method method = null;
			Class<?> type = null;
			Class<?>[] types = null;
			Object obj = null;
			String objStr = null;
			t = cls.newInstance();// 实例化这个类的一个对豿
			
			while(names.hasMoreElements()) {
				name = names.nextElement();
				
				obj = request.getParameter(name); //先判断参数是否为穿
				if(obj == null ) {
					continue;
				}
				
				objStr = String.valueOf(obj);
				if("".equals(objStr)) {
					continue;
				}
				
				methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
				method = setters.getOrDefault(methodName, null);
				if(method == null) {//说明该实体类中没有这个属性的注忼方泿
					continue;
				}
				
				//因为方法调用跟数据类型有关， 承有我们必须先获取这个方法的新参列衿
				types = method.getParameterTypes();
				if(types == null || types.length<=0) {//说明方法不带形参，那么我们页也无法注倿
					continue;
				}
				
				type = types[0];//因为我们提供Setter方法丿般都只有丿个形叿
				
				//反向濿活这个方法注倿
				if(Integer.TYPE == type || Integer.class == type ) {
					method.invoke(t, Integer.parseInt(objStr));
				}else if (Float.TYPE == type || Float.class == type) {
					method.invoke(t, Float.parseFloat(objStr));
				}else if(Double.TYPE == type || Double.class == type) {
					method.invoke(t, Double.parseDouble(objStr));
				}else {
					method.invoke(t, objStr);
				}			
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}catch(SecurityException e){
			e.printStackTrace();
		}catch(InstantiationException e) {
			e.printStackTrace();
		}catch(IllegalAccessException e) {
			e.printStackTrace();
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch(InvocationTargetException e) {
			e.printStackTrace();
		}
		return t;
	}

}
