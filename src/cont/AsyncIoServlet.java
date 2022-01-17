package cont;
 
import async.read.MyReadListener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
 
import java.io.*;

/**
 * 成功！！
 * 异步io 用于大文件上传，当有大文件上传时，io等待时间会较长，可以先返回一个数据
 * 然后根据io 异步处理，待到上传文件结束后，通知上传成功
 *curl -XPOST -F "file=@/usr/local/nacos/logs/tps-control.log.2021-11-10.0" --limit-rate 5k http://127.0.0.1:8080/async
 */
@WebServlet(urlPatterns="/async",asyncSupported=true)
public class AsyncIoServlet extends HttpServlet
{
    public void service(HttpServletRequest request ,
        HttpServletResponse response)
        throws IOException , ServletException
    {
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        out.println("<title>非阻塞IO示例</title>");
        out.println("进入Servlet的时间："
            + new java.util.Date() + ".<br/>");
        // 创建AsyncContext，开始异步调用
        AsyncContext context = request.startAsync();
        // 设置异步调用的超时时长
        context.setTimeout(60 * 1000);
        ServletInputStream input = request.getInputStream();
        // 为输入流注册监听器
        input.setReadListener(new MyReadListener(input, context));
        out.println("结束Servlet的时间："
            + new java.util.Date() + ".<br/>");
        out.flush();
    }
}