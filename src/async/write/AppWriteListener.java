package async.write;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author： hongzhi.xu
 * @Date: 2022/1/14 9:33 下午
 * @Version 1.0
 * 这是一个 回写监听器，当发生响应操作时，相应的生命周期会调用这里面的函数方法
 */
public class AppWriteListener implements WriteListener {
    private AsyncContext asyncContext;

    public AppWriteListener(AsyncContext context) {
        this.asyncContext = context;
    }

    @Override
    public void onWritePossible() throws IOException {
        ServletOutputStream outputStream = asyncContext.getResponse().getOutputStream();
        //如果往 ServletOutputStream 回写成功 ，则该方法返回 true，其他情况会返回 false。
        // 如果该方法返回 true，可以在 ServletOutputStream 上执行写操作。
        System.out.println("onWritePossible: outputStream.isReady() - " + outputStream.isReady());
        if (outputStream.isReady()) {
            ServletResponse resp = asyncContext.getResponse();
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("onWritePossible method ok</h1>");
        }
        asyncContext.complete();
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
