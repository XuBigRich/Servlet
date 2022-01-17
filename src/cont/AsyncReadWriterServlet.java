package cont;

import async.async.AppAsyncListener;
import async.async.AsyncRequestProcessor;
import async.read.AppReadListener;
import async.write.AppWriteListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * servlet的声明周期 是当 servlet 的url第一次被调用时 被初始化，
 *
 * 模拟一个 大型的文件上传 案例
 * curl -XPOST -F "file=@/usr/local/nacos/logs/tps-control.log.2021-11-10.0" --limit-rate 5k http://127.0.0.1:8080/asyncReadWriterServlet
 * @Author： hongzhi.xu
 * @Date: 2022/1/14 10:18 下午
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/asyncReadWriterServlet", asyncSupported = true)
public class AsyncReadWriterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //自己看一看super.doGet源码，便可知道若要实现get方法 就要覆盖父类的doGet方法，子类中 不可以执行。父类doGet方法
//        super.doGet(req, resp);
        //顺序不要错  立即同步返回一个response
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>is Read And Writer page ok</h1>");
        out.flush();
        //开启异步处理
        AsyncContext asyncCtx = req.startAsync();
        //设置一个读取监听器 ，异步读取输入流， 当读取完毕后 也会回调监听器中方法
        req.getInputStream().setReadListener(new AppReadListener(asyncCtx));
        //设置一个写入监听器 ，当异步写入完毕后 会回调这个监听器
//        resp.getOutputStream().setWriteListener(new AppWriteListener(asyncCtx));
//        asyncCtx.setTimeout(9000);//异步servlet的超时时间,异1步Servlet有对应的超时时间，如果在指定的时间内没有执行完操作，response依然会走原来Servlet的结束逻辑，后续的异步操作执行完再写回的时候，可能会遇到异常。
//        System.out.println(asyncCtx.getRequest().getParameter("time"));
        //直接输出到页面的内容(不等异步完成就直接给页面)

    }

}
