package Server.login;

import dao.Dao;
import vo.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName loginServer.java
 * @Description TODO
 * @createTime 2019年05月06日 18:46:00
 */
public class loginServer {
    //登录
    public static void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users u=new Users();
        u.setNam(request.getParameter("nam"));
        u.setPwd(request.getParameter("pwd"));
        Dao dao=new Dao();
        u=dao.login(u);
        if(u!=null){
            HttpSession ses=request.getSession();//获取session对象
            ses.setAttribute("u",u);//登录成功，在session保存当前用户信息，留待后用。
            response.sendRedirect("com?cmd=allContact");//相当于get方式发请求
        } else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            String res="<center>";
            res+="<font color=red>用户名或密码错误</font>";
            res+="<br><a href=index.jsp>重新登录</a>";
            res+="</center>";
            out.println(res);
        }
    }
}
