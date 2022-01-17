package cont;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(value = "/async-write", asyncSupported = true)
public class AsyncWriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //打印线程名称    （同步非阻塞）  同一个线程 操作，但是不会因为 io操作 阻塞住 后面的处理
        System.out.println("Servlet thread: " + Thread.currentThread().getName());
        AsyncContext asyncCtx = req.startAsync();
        ServletOutputStream os = resp.getOutputStream();
        //从本地获取文件输入流
        InputStream bigfileInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("time.js");
        //设置异步io 的监听器
        os.setWriteListener(new WriteListener() {

            @Override
            public void onWritePossible() throws IOException {

                int loopCount = 0;
                //打印线程名称    （同步非阻塞）  同一个线程 操作，但是不会因为 io操作 阻塞住 后面的处理
                System.out.println("WriteListener thread: " + Thread.currentThread().getName());
                //判断异步 输出流的状态
                while (os.isReady()) {
                    loopCount++;
                    System.out.println("Loop Count: " + loopCount);
                    //从io中读取二进制数组
                    byte[] bytes = readContent();
                    if (bytes != null) {
                        //将二进制数组写入到 异步输出流当中
                        os.write(bytes);
                    } else {
                        //如果 读不到二进制数组了 说明读取完毕，关闭 本地的输入流
                        closeInputStream();
                        //完成异步输出
                        asyncCtx.complete();
                        //跳出循环
                        break;
                    }
                }
            }

            @Override
            public void onError(Throwable t) {

                try {
                    os.print("Error happened");
                    os.print(t.getLocalizedMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeInputStream();
                    asyncCtx.complete();
                }

            }

            private byte[] readContent() throws IOException {
                byte[] bytes = new byte[1024];
                int readLength = bigfileInputStream.read(bytes);
                if (readLength <= 0) {
                    return null;
                }
                return bytes;
            }

            private void closeInputStream() {
                try {
                    bigfileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}