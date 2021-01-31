package com.yc.wowo.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.yc.wowo.util.StringUtil;

/**
 * 当应用程序已启动，我们就监听创建文件上传的目�?
 * @author Administrator
 *
 */
@WebListener
public class CreateUploadPathListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getInitParameter("uploadPath");
		if (StringUtil.checkNull(path)) {
			path = "images";
		}
		
		String basePath = sce.getServletContext().getRealPath("/"); //获取Tomcat在服务器中的绝对路径
		path = "../../" + path;
		File fl = new File(basePath, path);
		System.out.println(basePath);
		if (!fl.exists()) {
			fl.mkdirs();
		}
		
	}

}
