package com.yc.wowo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.GoodsInfo;
import com.yc.wowo.biz.IGoodsInfoBiz;

import com.yc.wowo.util.RequestParamUtil;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {
	@Autowired
	private IGoodsInfoBiz goodsInfoBizImpl;
	
	
	@RequestMapping("/findByFirst")
    public Map<String, Object> findByFirst(@RequestParam Map<String, Object>map) {
    	//要返回第一页的数据以及总记录数
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("total", goodsInfoBizImpl.total());
    	result.put("rows", goodsInfoBizImpl.finds(RequestParamUtil.findByPageUtil(map)));
    	return map;
    }
    
	@RequestMapping("/finds")
	public List<GoodsInfo> finds(@RequestParam Map<String, Object> map) {
		return goodsInfoBizImpl.finds(RequestParamUtil.findByPageUtil(map));
	}
    	
    /*	IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
    	//System.out.println("Goodop");
    	String type = request.getParameter("type");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int rows = Integer.parseInt(request.getParameter("rows"));
    	//System.out.println(goodsInfoBiz.finds(page, rows));
    	this.send(response, goodsInfoBiz.finds(type,page, rows));
    	*/
    
    
	/*private void findBySid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        gid = request.getParameter("gid");
		System.out.println("findBySid");
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		GoodsInfo goodsInfo = goodsInfoBiz.findByGid(gid);
		if(goodsInfo == null) {
			this.send(response, 400, null);
			return;
		}
		this.send(response, 200, goodsInfo);
	}*/

    public void findByGid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*gid = request.getParameter("gid");
		
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		GoodsInfo goodsInfo = goodsInfoBiz.findByGid(gid);
		if(goodsInfo == null) {
			this.send(response, 400, null);
			return;
		}
		this.send(response, 200, goodsInfo);*/
	}

	/*private void findByTid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//System.out.println("进入findByTid");
		IGoodsInfoDao  goods = new GoodsInfoDaoImpl();
		String tid = request.getParameter("tid");
		//System.out.println(tid);
		List<GoodsInfo> list = goods.findByTid(tid);
		if (list != null && !list.isEmpty()) { 
			this.send(response, 200, list);
			return;
		}
		this.send(response, 500, "失败");
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("updateop");
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
		    GoodsInfo goodsInfo = fileUploadUtil.uploads(GoodsInfo.class, pageContext);
		    IGoodsInfoDao goodsInfoBiz = new GoodsInfoDaoImpl();
		    int result = goodsInfoBiz.update(goodsInfo);
		    //System.out.println(goodsInfo);
		    if (result > 0) {
		    	this.send(response, 200, "成功");
		    	return;
		    }
		    this.send(response, 500, "失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

    public void findCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*System.out.println("进入goodsfindCondition");
		String sid = request.getParameter("sid");
		System.out.println(sid);
		String gname = request.getParameter("gname");
		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByCondition(sid, gname, status, page, rows));
		*/
	}

    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			Map<String, String> map = fileUploadUtil.uploads(pageContext);
			
			result.put("filename", "图片");
			result.put("url", "../../" + map.get("upload"));
			result.put("uploaded", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    this.send(response, result);*/
	}

    public void add(HttpServletRequest request, HttpServletResponse response)  {
		/*FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
		    GoodsInfo goodsInfo = fileUploadUtil.uploads(GoodsInfo.class, pageContext);
			System.out.println(goodsInfo);
			
			IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
			int result = goodsInfoBiz.add(goodsInfo);
			int gi = goodsInfoBiz.GoodID();
			String gid = String.valueOf(gi);
			int si = goodsInfo.getSid();
			String sid = String.valueOf(si);
			String gname = goodsInfo.getGname();
			String pics = goodsInfo.getPics();
			String intro = goodsInfo.getIntro();
			Double prices = goodsInfo.getPrice();
			String price = String.valueOf(prices);
			Double rebates = goodsInfo.getRebate();
			String rebate = String.valueOf(rebates);
			Directory directory = FSDirectory.open(Paths.get("G:\\online\\Lucene_db\\article_tb"));
			
			//创建分词器
			Analyzer analyzer = new SmartChineseAnalyzer();			
			//创建IndexWriterConfig实例,通过IndexWriterConfig实例配置索引创建模式
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			//设置索引的创建模式
			config.setOpenMode(OpenMode.CREATE_OR_APPEND);
			
			IndexWriter indexWriter = new IndexWriter(directory, config);

			
			//在Lucene中一个Document实例代表一条记录
			Document doc = new Document();
			
		
		    if (result > 0) {
		    	doc.add(new StringField("gid", gid, Store.YES));
				doc.add(new TextField("sid", sid, Store.YES));
				doc.add(new TextField("game", gname, Store.YES));
				doc.add(new TextField("pics", pics, Store.YES));
				doc.add(new TextField("intro", intro, Store.YES));
				doc.add(new TextField("price", price, Store.YES));
				doc.add(new TextField("rebate", rebate, Store.YES));
				//将索引挟制索引库
				indexWriter.addDocument(doc);
				//提交
				indexWriter.commit();
				//关闭
				indexWriter.close();
				System.out.println("添加成功");
				System.out.println(doc);
			   
		    	this.send(response, 200, "成功");
		    	return;
		    }
		    this.send(response, 500, "失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	



    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByPage(page, rows));*/
		
	}

	
}
