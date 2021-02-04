package com.yc.wowo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.wowo.bean.ShopInfo;
import com.yc.wowo.biz.IShopInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.RequestParamUtil;



@RestController
@RequestMapping("/shop")
public class ShopInfoController {
	@Autowired 
	private IShopInfoBiz shopInfoBizImpl;
	
	@RequestMapping("/finds")
	public List<ShopInfo> finds() {
		return shopInfoBizImpl.finds();	
	}

	@RequestMapping("/findBySid")
	public ResultDTO findBySid(String sid) {
		ShopInfo shopInfo = shopInfoBizImpl.findBySid(sid);
		if(shopInfo == null) {
			return new ResultDTO(400);
		}
		return new ResultDTO(200, shopInfo);
	}

	@RequestMapping("/findCondition")
	public JsonObject findCondition(@RequestParam Map<String, Object> map) {
		return shopInfoBizImpl.findByCondition(RequestParamUtil.findByPageUtil(map));
	}

	@RequestMapping("/add")
	public ResultDTO add(ShopInfo sf, MultipartFile license_pic, MultipartFile[] shop_pic, HttpServletRequest request) {
		String path = request.getServletContext().getInitParameter("uploadPath");
		System.out.println(path);
		String basePath = request.getServletContext().getRealPath("");
		System.out.println(basePath);
		String savePath = "";
		File dest = null;
		if(license_pic != null && license_pic.getSize() > 0) {
			try {
				savePath = path + "/" + new Date().getTime() + "_" + license_pic.getOriginalFilename();
				dest = new File(new File(basePath).getParentFile(), savePath);
				license_pic.transferTo(dest);
				sf.setLicense("../" + savePath);
				System.out.println(savePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(shop_pic != null && shop_pic.length > 0 && shop_pic[0].getSize() > 0) {
			String picStr = "";
			try {
				for(MultipartFile pic : shop_pic) {
					savePath = path + "/" + new Date().getTime() + "_" + pic.getOriginalFilename();
					dest = new File(new File(basePath).getParentFile(), savePath);
					pic.transferTo(dest);
					if("".equals(picStr)) {
						picStr += "../" + savePath;
					}else{
						picStr += ";../" + savePath;
					}
				}
				sf.setPics(picStr);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sf);
		int result = shopInfoBizImpl.add(sf);
		System.out.println(result);
		if(result > 0) {
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");		
	}
	
	
	@RequestMapping("/findByPage")
	public JsonObject findByPage(@RequestParam Map<String, Object> map) {
		return shopInfoBizImpl.findByPage(RequestParamUtil.findByPageUtil(map));
	}
	/*
	@RequestMapping("/finds")
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//System.out.println("updateop");
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
		    ShopInfo shopInfo = fileUploadUtil.uploads(ShopInfo.class, pageContext);
		    IShopInfoDao shopInfoBiz = new ShopInfoDaoImpl();
		    int result = shopInfoBiz.update(shopInfo);
		    if (result > 0) {
		    	this.send(response, 200, "成功");
		    	return;
		    }
		    this.send(response, 500, "失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	
}
