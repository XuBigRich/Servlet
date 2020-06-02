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

//ʹ��ע��@MultipartConfig��һ��Servlet��ʶΪ֧���ļ��ϴ� ����web.xml <multipart-config/>
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
	//ɾ����ϵ��
	public void delContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		dao.delContact(cid);
		response.sendRedirect("com?cmd=allContact");
	}
	//���ǰ
	public void beforeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		List ts=dao.allTypes();
		request.setAttribute("ts", ts);
		request.getRequestDispatcher("addcontact.jsp").forward(request, response);
	}
	//�޸�ǰ
	public void beforeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		Dao dao=new Dao();
		Contact c=dao.findContact(cid);//����ID��ѯ���޸ĵ���ϵ�˶���
		List ts=dao.allTypes();//�������
		request.setAttribute("c", c);
		request.setAttribute("ts", ts);
		response.setHeader("xhz", "XXX");
		request.getRequestDispatcher("updatecontact.jsp").forward(request, response);
	}
	
	//��ϵ���б�
	public void allContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		Dao dao=new Dao();
		
		List cs=dao.allContact(u.getSid());//��ѯ��ǰ��½�ߵ���ϵ���б�
		int p=1;//Ĭ����ʾ��һҳ
		if(request.getParameter("p")!=null){
			p=Integer.parseInt(request.getParameter("p"));
		}
		PageBean pb=new PageBean();//pagesizeĬ��=5
		pb.setData(cs);//�������ݼ���
		pb.setCount(cs.size());//���ü�¼������
		pb.setP(p);//���õ�ǰ����ʾ��ҳ��

		
		List ts=dao.allTypes();//��ѯ�������
		
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
	//�������������ģ����ѯ��ϵ��

	public void findContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao=new Dao();
		Contact c=(Contact)request.getAttribute("c");//ҳ�洫�ݹ����Ĳ�ѯ����
		
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");
		c.setSid(u.getSid());//��ǰ��½��ID
		
		List cs=dao.findContact(c);//������ģ����ѯ
		List ts=dao.allTypes();//�������
		
		request.setAttribute("cs", cs);
		request.setAttribute("ts",ts);
		request.getRequestDispatcher("allcontact.jsp").forward(request, response);
	}
	//�޸���ϵ��
	public void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		Dao dao=new Dao();
		dao.updateContact(c);
		response.sendRedirect("com?cmd=allContact");
	}
	//�����ϵ��
	public void addContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contact c=(Contact)request.getAttribute("c");
		HttpSession ses=request.getSession();
		Users u=(Users)ses.getAttribute("u");//��ǰ��¼�߶���
		Dao dao=new Dao();
		c.setSid(u.getSid());//����ӵ���ϵ�ˣ��ǵ�ǰ��¼�ߵ���ϵ��
		dao.addContact(c);
		response.sendRedirect("com?cmd=allContact");
	}


}
