package cont;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * curl -XPOST -F "file=@/usr/local/nacos/logs/tps-control.log.2021-11-10.0" --limit-rate 5k http://127.0.0.1:8080/async-read
 */
@WebServlet(value = "/async-read", asyncSupported = true)
public class AsyncReadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>is Read And Writer page ok</h1>");
        out.flush();
        //打印线程名称    （同步非阻塞）  同一个线程 ，但是不会因为 io操作 阻塞住 后面的处理
        System.out.println("Servlet thread: " + Thread.currentThread().getName());
        AsyncContext asyncCtx = req.startAsync();
        ServletInputStream is = asyncCtx.getRequest().getInputStream();
        is.setReadListener(new ReadListener() {
            private int totalReadBytes = 0;

            @Override
            public void onDataAvailable() {
                //打印线程名称  （同步非阻塞） 同一个线程 ，但是不会因为 io操作 阻塞住 后面的处理
                System.out.println("ReadListener thread: " + Thread.currentThread().getName());

                try {
                    byte buffer[] = new byte[1 * 1024];
                    int readBytes = 0;
                    while (is.isReady() && !is.isFinished()) {
                        int length = is.read(buffer);
                        if (length == -1 && is.isFinished()) {
                            asyncCtx.complete();
                            System.out.println("Read: " + readBytes + " bytes");
                            System.out.println("Total Read: " + totalReadBytes + " bytes");
                            return;
                        }
                        readBytes += length;
                        totalReadBytes += length;

                    }
                    System.out.println("Read: " + readBytes + " bytes");

                } catch (IOException ex) {
                    ex.printStackTrace();
                    asyncCtx.complete();
                }
            }

            @Override
            public void onAllDataRead() {
                try {
                    System.out.println("Total Read: " + totalReadBytes + " bytes");
                    asyncCtx.getResponse().getWriter().println("Finished");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                asyncCtx.complete();
            }

            @Override
            public void onError(Throwable t) {
                asyncCtx.complete();
            }
        });

    }

}