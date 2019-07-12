package cont;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName servletConfigTest.java
 * @Description TODO
 * @createTime 2019年05月24日 10:28:00
 */
public class servletConfigTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig=getServletConfig();
        /*获取servlet名称*/
        String srevlet= servletConfig.getServletName();
        System.out.println(srevlet);
        /*获取具体的某个参数*/
        String name=servletConfig.getInitParameter("name");
        System.out.println("name: "+name);
        /*获取所有参数名称*/
        Enumeration<String> names=servletConfig.getInitParameterNames();
        while (names.hasMoreElements()){
            String name2=names.nextElement();
            System.out.println(name2);
        }
    }
}
