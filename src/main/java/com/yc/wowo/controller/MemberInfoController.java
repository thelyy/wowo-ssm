package com.yc.wowo.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.MemberInfo;
import com.yc.wowo.biz.IMemberInfoBiz;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.JavaMailUtil;
import com.yc.wowo.util.RandomUtil;
import com.yc.wowo.util.SessionKeyConstant;
import com.yc.wowo.util.htmlText;

@RestController
@RequestMapping("/login")
public class MemberInfoController {
	@Autowired
	private IMemberInfoBiz memberInfoBizImpl;
	public static String account;
	public static Integer mid;
	public static String nickName;
	public static String realName; 
	public static String status; 
	
	@RequestMapping("/check")
	public ResultDTO check (HttpSession session) throws IOException {
		Object obj = session.getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		if (obj == null) {
			return new ResultDTO(500, "失败");
		}
		return new ResultDTO(200, obj);
	}
	
	/*
	@RequestMapping("/code")
	public ResultDTO code(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		String sessionCode = (String) request.getSession().getAttribute("code");
		String codes = request.getParameter("codes");
		session.setAttribute(SessionKeyConstant.EMAILCODE, codes);
			return new ResultDTO(200, codes);
	}*/
	
	@RequestMapping("/SendEmailServlet")
	public void doPost(HttpServletRequest req, HttpServletResponse resp, HttpSession session1) {
		try {
			String email = req.getParameter("email");
			JavaMailUtil.receiveMailAccount = email; // 给用户输入的邮箱发送邮件
 
			// 1、创建参数配置，用于连接邮箱服务器的参数配置
			Properties props = new Properties();
			// 开启debug调试
			props.setProperty("mail.debug", "true");
			// 发送服务器需要身份验证
			props.setProperty("mail.smtp.auth", "true");
			// 设置右键服务器的主机名
			props.setProperty("mail.host", JavaMailUtil.emailSMTPHost);
			// 发送邮件协议名称
			props.setProperty("mail.transport.protocol", "smtp");
 
			// 2、根据配置创建会话对象，用于和邮件服务器交互
			Session session = Session.getInstance(props);
			// 设置debug，可以查看详细的发送log
			session.setDebug(true);
			// 3、创建一封邮件
			String code = RandomUtil.getRandom();
			System.out.println("邮箱验证码：" + code);
			String html = htmlText.html(code);
			MimeMessage message = JavaMailUtil.creatMimeMessage(session, JavaMailUtil.emailAccount,
					JavaMailUtil.receiveMailAccount, html);
			// 4、根据session获取邮件传输对象
			Transport transport = session.getTransport();
			// 5、使用邮箱账号和密码连接邮箱服务器emailAccount必须与message中的发件人邮箱一致，否则报错
			transport.connect(JavaMailUtil.emailAccount, JavaMailUtil.emailPassword);
			// 6、发送邮件,发送所有收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			// 7、关闭连接
			transport.close();
			//  写入session
			session1.setAttribute(SessionKeyConstant.EMAILCODE, code);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("error", "邮件发送失败");
		}	
	}
	
	@RequestMapping("/logins")
	public ResultDTO logins(@RequestParam Map<String, Object> map, HttpSession session, @RequestParam ("nickName") String account, String password ) {
		//System.out.println("进入op");
		//获取用户信息
		//String account = req.getParameter("account");
		
		//String password = req.getParameter("password");

		MemberInfo member = memberInfoBizImpl.finds(account);
		if(member == null) {
			return new ResultDTO(400, "用户名不存在");
			//System.out.println("用户名不存在");
		}
		int status = member.getStatus();
		if(status == 0) {
			return new ResultDTO(300, "该账户已冻结");
			//System.out.println("该账户已冻结");
		}
		
		//MemberInfo mf = IMemberInfoBiz.login(account,password);
	
		//判断
		MemberInfo mf = memberInfoBizImpl.login(map);
		if(mf == null) {//登录失败
			return new ResultDTO(500, "密码错误");
		}
		session.setAttribute("MemberInfoController", account);
		session.setAttribute(SessionKeyConstant.MEMBERINFOLOGIN,mf);
		return new ResultDTO(200, "成功");
	}
	
	
	@RequestMapping("/add")
	public ResultDTO add(MemberInfo mf, HttpSession session, String code )  {
		String scode = String.valueOf(session.getAttribute(SessionKeyConstant.EMAILCODE));
		if(code.equalsIgnoreCase(scode)) {
			if(memberInfoBizImpl.add(mf) > 0) {
				System.out.println(mf);
				return new ResultDTO(200, "成功"); 
			}else {
				return new ResultDTO(500, "失败");
			}
		}else {
			return new ResultDTO(300, "失败");
		}
	}
	
	
	/*
	private void findPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		MemberinfoDao memberinfoBiz = new MemberInfodaoImpl();
		MemberInfo mf = (MemberInfo) obj;
		if(mf == null) {
			this.send(response, 400 , "失败");
		}
		String nickName = mf.getNickName();
	
		System.out.println(nickName);
		MemberInfo MemberInfo = memberinfoBiz.finds(nickName);
		if(MemberInfo == null) {//登录失败
			this.send(response, 300 , "失败");
		}else {
			this.send(response, 200 , MemberInfo);
			request.getSession().setAttribute(SessionKeyConstant.MEMBERINFOLOGIN,MemberInfo);
			System.out.println("成功");
		}
		
	}



	private void findByMid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		MemberinfoDao memberinfoBiz = new MemberInfodaoImpl();
		MemberInfo mf = (MemberInfo) obj;
		int mid = mf.getMid();
		MemberInfo MemberInfo = memberinfoBiz.findByMid(mid);
		if(MemberInfo == null) {//登录失败
			this.send(response, 400 , "失败");
		}else {
			this.send(response, 200 , MemberInfo);
			System.out.println("成功");
		}
		
	}



	private void daochu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("进入daochu");
		//1.创建一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//2.创建一个工作表
		HSSFSheet sheet = workbook.createSheet("用户信息表");
		
		
		
		//3.创建行，并在行中写入数据(表头)
		String[] title= {"会员编号","会员昵称","会员名称","会员密码","联系方式","会员邮箱","类型状态"};
		
		HSSFRow row = sheet.createRow(0);//从0开始
		
		sheet.setColumnWidth(4, 13*256);
		sheet.setColumnWidth(5, 20*256);
		
		for(int i=0;i<title.length;i++) {
			HSSFCell cell = row.createCell(i);//01234
			
			cell.setCellValue(title[i]);
		}
		MemberinfoDao adminInfoDao = new MemberInfodaoImpl();
		List<MemberInfo> list = adminInfoDao.findAll();
		System.out.println(list);
		
		System.out.println(list);
		for(int i =0;i<list.size();i++) {
			HSSFRow row2 = sheet.createRow(i+1);
			
			MemberInfo type = list.get(i);
			int Astatus =type.getStatus();
			if(Astatus==0) {
				 status = "已注销";
			}else {
				 status = "在线";
			}
			
			HSSFCell cell1 = row2.createCell(0);
			cell1.setCellValue(type.getMid());
			
			HSSFCell cell2 = row2.createCell(1);
			cell2.setCellValue(type.getNickName());
			
			HSSFCell cell3 = row2.createCell(2);
			cell3.setCellValue(type.getRealName());
			
			HSSFCell cell4 = row2.createCell(3);
			cell4.setCellValue(type.getPwd());
			
			HSSFCell cell5 = row2.createCell(4);
			cell5.setCellValue(type.getTel());
			
			HSSFCell cell6 = row2.createCell(5);
			cell6.setCellValue(type.getEmail());
			
			HSSFCell cell7 = row2.createCell(6);
			cell7.setCellValue(status);	
		}
		
		File file = new File("G:\\member.xls");
		
		OutputStream outputStream = new FileOutputStream(file);

		workbook.write(outputStream);
		
		outputStream.close();
		this.send(response, 200,"成功");
		
		
	}



	private void updateEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String account = request.getParameter("account");
		String email = request.getParameter("email");
		System.out.println(email);
		String sessionCode = (String) request.getSession().getAttribute("code");
		String codes = request.getParameter("codes");
		MemberinfoDao memberinfoBiz = new MemberInfodaoImpl();
		MemberInfo MemberInfo = memberinfoBiz.finds(account);
		if(MemberInfo == null) {
			this.send(response, 300, "该用户名不存在");
		}
		System.out.println("进入updateEmail");
		String Email = MemberInfo.getEmail();
		System.out.println(Email);
		if(!email.equals(Email)) {
			this.send(response, 500, "邮箱不正确");
		}
		if(sessionCode.toLowerCase().equals(codes.toLowerCase())) {
			this.send(response, 200, "成功");
		}
			this.send(response, 400, "你输入的验证码不正确");
		
	}



	private void updatepwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("updatepwd");
		
		String nickName = request.getParameter("nickName");
		String pwd = request.getParameter("pwd");
		MemberinfoDao memberInfoDao = new MemberInfodaoImpl();
		int result = memberInfoDao.updatepwd(pwd, nickName);
	
		if (result > 0) { //说明成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
		
	}



	private void updates(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberInfo MemberInfo = RequestParamUtil.getParams(MemberInfo.class, request);
		//调用业务模型层，处理
		MemberinfoDao memberInfoDao = new MemberInfodaoImpl();
		nickName = request.getParameter("nickName");
		realName = request.getParameter("realName");
		System.out.println(nickName);
	
		int result = memberInfoDao.updates(MemberInfo);
		
		System.out.println(MemberInfo);
		if (result > 0) { //说明成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberinfoDao adminInfoDao = new MemberInfodaoImpl();
		this.send(response, adminInfoDao.findAll());
	}



	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		MemberinfoDao adminInfoDao = new MemberInfodaoImpl();
		this.send(response, adminInfoDao.findByPage(page, rows));
	}



	private void update(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		Object obj = request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		try {
			MemberInfo mf = (MemberInfo) obj;
			mid = mf.getMid();
			MemberInfo MemberInfo = fileUploadUtil.uploads(MemberInfo.class, pageContext);
			MemberinfoDao dao = new MemberInfodaoImpl();
			System.out.println("进入updateop");
		    int result = dao.update(MemberInfo);
		    System.out.println(MemberInfo);
		    if (result > 0) {
		    	this.send(response, 200, "成功");
		    }
		    this.send(response, 500, "失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}






	private void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nickName = request.getParameter("nickName");
		MemberinfoDao memberinfoBiz = new MemberInfodaoImpl();
		MemberInfo MemberInfo = memberinfoBiz.finds(nickName);
		if(MemberInfo == null) {//登录失败
			this.send(response, 400 , "失败");
		}else {
			this.send(response, 200 , MemberInfo);
			request.getSession().setAttribute(SessionKeyConstant.MEMBERINFOLOGIN,MemberInfo);
			System.out.println("成功");
		}
	}








	
	*/
	
}
