package cont;

import async.async.AppAsyncListener;
import async.read.AppReadListener;
import async.write.AppWriteListener;
import async.async.AsyncRequestProcessor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName servletConfigTest.java
 * @Description TODO
 * @createTime 2019年05月24日 10:28:00
 */
@WebServlet(urlPatterns = "/asyncRunningServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        //这个和 注解上面的asyncSupported = true 起到的效果一样
        req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        //获取time参数
        String time = req.getParameter("time");
        if(time==null){
            time="3";
        }
        //将time参数类型由String 转为int
        int secs = Integer.valueOf(time);
        // max 10 seconds
        //最大十秒 不能再大了
        if (secs > 10000)
            secs = 10000;

        AsyncContext asyncCtx = req.startAsync();
        //设置一个监听器,当异步请求处理完毕后 会回调这个监听器
        asyncCtx.addListener(new AppAsyncListener());
        asyncCtx.setTimeout(9000);//异步servlet的超时时间,异步Servlet有对应的超时时间，如果在指定的时间内没有执行完操作，response依然会走原来Servlet的结束逻辑，后续的异步操作执行完再写回的时候，可能会遇到异常。
        //获取线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) req
                .getServletContext().getAttribute("executor");
        //线程池执行异步请求处理
        executor.execute(new AsyncRequestProcessor(asyncCtx, secs));
        long endTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet End::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId() + "::Time Taken="
                + (endTime - startTime) + " ms.");

    }
}
