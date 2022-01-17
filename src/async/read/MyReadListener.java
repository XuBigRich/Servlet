package async.read;

import javax.servlet.*;
import java.io.*;


public class MyReadListener implements ReadListener {
    private ServletInputStream input;
    private AsyncContext context;

    public MyReadListener(ServletInputStream input, AsyncContext context) {
        this.input = input;
        this.context = context;
    }

    @Override
    public void onDataAvailable() {
        System.out.println("数据可用！！，快点开始接收数据吧！");
        try {
            // 暂停5秒，模拟读取数据是一个耗时操作。
            Thread.sleep(5000);
            StringBuilder sb = new StringBuilder();
            int len = -1;
            byte[] buff = new byte[1024];
            // 采用原始IO方式读取浏览器向Servlet提交的数据
            while (input.isReady() && (len = input.read(buff)) > 0) {
                String data = new String(buff, 0, len);
                sb.append(data);
            }
            System.out.println(sb);
            // 将数据设置为request范围的属性
            // 转发到视图页面
            PrintWriter printWriter = context.getResponse().getWriter();
            printWriter.write("<h1> 数据读好了</h1>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onAllDataRead() {
        System.out.println("数据读取完成");
        context.complete();
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}