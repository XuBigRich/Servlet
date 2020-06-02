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

//使用注解@MultipartConfig将一个Servlet标识为支持文件上传 代替web.xml <multipart-config/>
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
	//删除联系人
	public void delContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		dao.delContact(cid);
		response.sendRedirect("com?cmd=allContact");
	}
	//添加前
	public void beforeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		List ts=dao.allTypes();
		request.setAttribute("ts", ts);
		request.getRequestDispatcher("addcontact.jsp").forward(request, response);
	}
	//修改前
	public void beforeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		Contact c=dao.findContact(cid);//根据ID查询待修改的联系人对象
		List ts=dao.allTypes();//所有类别
		request.setAttribute("c", c);
		request.setAttribute("ts", ts);
		response.setHeader("xhz", "XXX");
		request.getRequestDispatcher("updatecontact.jsp").forward(request, response);
	}
	
	//联系人列表
	public void allContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		Dao dao=new Dao();
		
		List cs=dao.allContact(u.getSid());//查询当前登陆者的联系人列表
		int p=1;//默认显示第一页
		if(request.getParameter("p")!=null){
			p=Integer.parseInt(request.getParameter("p"));
		}
		PageBean pb=new PageBean();//pagesize默认=5
		pb.setData(cs);//设置数据集合
		pb.setCount(cs.size());//设置记录总条数
		pb.setP(p);//设置当前待显示的页码

		
		List ts=dao.allTypes();//查询所有类别
		
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
	//多条件任意组合模糊查询联系人

	public void findContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		Contact c=(Contact)request.getAttribute("c");//页面传递过来的查询条件
		
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		c.setSid(u.getSid());//当前登陆者ID
		
		List cs=dao.findContact(c);//按条件模糊查询
		List ts=dao.allTypes();//所有类别
		
		request.setAttribute("cs", cs);
		request.setAttribute("ts",ts);
		request.getRequestDispatcher("allcontact.jsp").forward(request, response);
	}
	//修改联系人
	public void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		Dao dao=new Dao();
		dao.updateContact(c);
		response.sendRedirect("com?cmd=allContact");
	}
	//添加联系人
	public void addContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");//当前登录者对象
		Dao dao=new Dao();
		c.setSid(u.getSid());//待添加的联系人，是当前登录者的联系人
		dao.addContact(c);
		response.sendRedirect("com?cmd=allContact");
	}


}
