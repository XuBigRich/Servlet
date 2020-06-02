<<<<<<< HEAD
package cont;

import Server.download.Download;
import Server.login.loginServer;
import Server.statistics.PieServer;
import Server.upload.UploadServer;
import dao.Dao;
import tools.PageBean;
import vo.Contact;
import vo.Users;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//Ê¹ÓÃ×¢½â@MultipartConfig½«Ò»¸öServlet±êÊ¶ÎªÖ§³ÖÎÄ¼şÉÏ´« ´úÌæweb.xml <multipart-config/>
//@MultipartConfig
public class ComServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("beforeAdd"))beforeAdd(request,response);
		else if(cmd.equals("beforeUpdate"))beforeUpdate(request,response);
		else if(cmd.equals("delContact"))delContact(request,response);
		else if(cmd.equals("allContact"))allContact(request,response);
		else if(cmd.equals("allfile"))Download.allFiles(request,response);
		else if(cmd.equals("download"))Download.download(request,response);
        else if(cmd.equals("pieservlet"))PieServer.paint(request,response);
	}
	//É¾³ıÁªÏµÈË
	public void delContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		dao.delContact(cid);
		response.sendRedirect("com?cmd=allContact");
	}
	//Ìí¼ÓÇ°
	public void beforeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		List ts=dao.allTypes();
		request.setAttribute("ts", ts);
		request.getRequestDispatcher("addcontact.jsp").forward(request, response);
	}
	//ĞŞ¸ÄÇ°
	public void beforeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		Contact c=dao.findContact(cid);//¸ù¾İID²éÑ¯´ıĞŞ¸ÄµÄÁªÏµÈË¶ÔÏó
		List ts=dao.allTypes();//ËùÓĞÀà±ğ
		request.setAttribute("c", c);
		request.setAttribute("ts", ts);
		response.setHeader("xhz", "XXX");
		request.getRequestDispatcher("updatecontact.jsp").forward(request, response);
	}
	
	//ÁªÏµÈËÁĞ±í
	public void allContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		Dao dao=new Dao();
		
		List cs=dao.allContact(u.getSid());//²éÑ¯µ±Ç°µÇÂ½ÕßµÄÁªÏµÈËÁĞ±í
		int p=1;//Ä¬ÈÏÏÔÊ¾µÚÒ»Ò³
		if(request.getParameter("p")!=null){
			p=Integer.parseInt(request.getParameter("p"));
		}
		PageBean pb=new PageBean();//pagesizeÄ¬ÈÏ=5
		pb.setData(cs);//ÉèÖÃÊı¾İ¼¯ºÏ
		pb.setCount(cs.size());//ÉèÖÃ¼ÇÂ¼×ÜÌõÊı
		pb.setP(p);//ÉèÖÃµ±Ç°´ıÏÔÊ¾µÄÒ³Âë

		
		List ts=dao.allTypes();//²éÑ¯ËùÓĞÀà±ğ
		
		request.setAttribute("pb", pb);
		request.setAttribute("ts", ts);
		request.getRequestDispatcher("allcontact.jsp").forward(request, response);
	}
	//======================================================================================================================
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("login"))loginServer.login(request,response);
		else if(cmd.equals("addContact"))addContact(request,response);
		else if(cmd.equals("updateContact"))updateContact(request,response);
		else if(cmd.equals("findContact"))findContact(request,response);
		else if(cmd.equals("uploadServlet"))UploadServer.uploadServletfindContact(request,response);
	}
	//¶àÌõ¼şÈÎÒâ×éºÏÄ£ºı²éÑ¯ÁªÏµÈË

	public void findContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		Contact c=(Contact)request.getAttribute("c");//Ò³Ãæ´«µİ¹ıÀ´µÄ²éÑ¯Ìõ¼ş
		
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		c.setSid(u.getSid());//µ±Ç°µÇÂ½ÕßID
		
		List cs=dao.findContact(c);//°´Ìõ¼şÄ£ºı²éÑ¯
		List ts=dao.allTypes();//ËùÓĞÀà±ğ
		
		request.setAttribute("cs", cs);
		request.setAttribute("ts",ts);
		request.getRequestDispatcher("allcontact.jsp").forward(request, response);
	}
	//ĞŞ¸ÄÁªÏµÈË
	public void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		Dao dao=new Dao();
		dao.updateContact(c);
		response.sendRedirect("com?cmd=allContact");
	}
	//Ìí¼ÓÁªÏµÈË
	public void addContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");//µ±Ç°µÇÂ¼Õß¶ÔÏó
		Dao dao=new Dao();
		c.setSid(u.getSid());//´ıÌí¼ÓµÄÁªÏµÈË£¬ÊÇµ±Ç°µÇÂ¼ÕßµÄÁªÏµÈË
		dao.addContact(c);
		response.sendRedirect("com?cmd=allContact");
	}


}
=======
package cont;

import Server.download.Download;
import Server.login.loginServer;
import Server.statistics.PieServer;
import Server.upload.UploadServer;
import dao.Dao;
import tools.PageBean;
import vo.Contact;
import vo.Users;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//ä½¿ç”¨æ³¨è§£@MultipartConfigå°†ä¸€ä¸ªServletæ ‡è¯†ä¸ºæ”¯æŒæ–‡ä»¶ä¸Šä¼  ä»£æ›¿web.xml <multipart-config/>
//@MultipartConfig
public class ComServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("beforeAdd"))beforeAdd(request,response);
		else if(cmd.equals("beforeUpdate"))beforeUpdate(request,response);
		else if(cmd.equals("delContact"))delContact(request,response);
		else if(cmd.equals("allContact"))allContact(request,response);
		else if(cmd.equals("allfile"))Download.allFiles(request,response);
		else if(cmd.equals("download"))Download.download(request,response);
        else if(cmd.equals("pieservlet"))PieServer.paint(request,response);
	}
  //åˆ é™¤è”ç³»äºº
	public void delContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		dao.delContact(cid);
		response.sendRedirect("com?cmd=allContact");
	}
	//æ·»åŠ å‰
	public void beforeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		List ts=dao.allTypes();
		request.setAttribute("ts", ts);
		request.getRequestDispatcher("addcontact.jsp").forward(request, response);
	}
	//ä¿®æ”¹å‰
	public void beforeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		Contact c=dao.findContact(cid);//ï¿½ï¿½ï¿½ï¿½IDï¿½ï¿½Ñ¯ï¿½ï¿½ï¿½Ş¸Äµï¿½ï¿½ï¿½Ïµï¿½Ë¶ï¿½ï¿½ï¿½
		List ts=dao.allTypes();//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		request.setAttribute("c", c);
		request.setAttribute("ts", ts);
		response.setHeader("xhz", "XXX");
		request.getRequestDispatcher("updatecontact.jsp").forward(request, response);
	}
	
	//è”ç³»äººåˆ—è¡¨
	public void allContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		Dao dao=new Dao();
		
		List cs=dao.allContact(u.getSid());//æŸ¥è¯¢å½“å‰ç™»é™†è€…çš„è”ç³»äººåˆ—è¡¨
		int p=1;//é»˜è®¤æ˜¾ç¤ºç¬¬ä¸€é¡µ
		if(request.getParameter("p")!=null){
			p=Integer.parseInt(request.getParameter("p"));
		}
		PageBean pb=new PageBean();//pagesizeé»˜è®¤=5
		pb.setData(cs);//è®¾ç½®æ•°æ®é›†åˆ
		pb.setCount(cs.size());//è®¾ç½®è®°å½•æ€»æ¡æ•°
		pb.setP(p);//è®¾ç½®å½“å‰å¾…æ˜¾ç¤ºçš„é¡µç 

		
		List ts=dao.allTypes();//æŸ¥è¯¢æ‰€æœ‰ç±»åˆ«
		
		request.setAttribute("pb", pb);
		request.setAttribute("ts", ts);
		request.getRequestDispatcher("allcontact.jsp").forward(request, response);
	}
	//======================================================================================================================
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getParameter("cmd");
		if(cmd.equals("login"))loginServer.login(request,response);
		else if(cmd.equals("addContact"))addContact(request,response);
		else if(cmd.equals("updateContact"))updateContact(request,response);
		else if(cmd.equals("findContact"))findContact(request,response);
		else if(cmd.equals("uploadServlet"))UploadServer.uploadServletfindContact(request,response);
	}
	//å¤šæ¡ä»¶ä»»æ„ç»„åˆæ¨¡ç³ŠæŸ¥è¯¢è”ç³»äºº

	public void findContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		Contact c=(Contact)request.getAttribute("c");//é¡µé¢ä¼ é€’è¿‡æ¥çš„æŸ¥è¯¢æ¡ä»¶
		
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		c.setSid(u.getSid());//å½“å‰ç™»é™†è€…ID
		
		List cs=dao.findContact(c);//æŒ‰æ¡ä»¶æ¨¡ç³ŠæŸ¥è¯¢
		List ts=dao.allTypes();//æ‰€æœ‰ç±»åˆ«
		
		request.setAttribute("cs", cs);
		request.setAttribute("ts",ts);
		request.getRequestDispatcher("allcontact.jsp").forward(request, response);
	}
	//ä¿®æ”¹è”ç³»äºº
	public void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		Dao dao=new Dao();
		dao.updateContact(c);
		response.sendRedirect("com?cmd=allContact");
	}
	//æ·»åŠ è”ç³»äºº
	public void addContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");//å½“å‰ç™»å½•è€…å¯¹è±¡
		Dao dao=new Dao();
		c.setSid(u.getSid());//å¾…æ·»åŠ çš„è”ç³»äººï¼Œæ˜¯å½“å‰ç™»å½•è€…çš„è”ç³»äºº
		dao.addContact(c);
		response.sendRedirect("com?cmd=allContact");
	}



}
>>>>>>> f755fd64db48b127135e88fd8ca6f213464fbd42
