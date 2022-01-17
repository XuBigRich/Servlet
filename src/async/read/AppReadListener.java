package async.read;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author： hongzhi.xu
 * @Date: 2022/1/14 9:18 下午
 * @Version 1.0
 * 这是一个 读取监听器，当发生读取操作时，读取的生命周期会调用这里面的函数方法
 * ServletInputStream 的方法 boolean isReady().如果可以无阻塞地读取数据 isReady 方法返回 true
 *
 *
 * boolean isFinished().
 *      ServletInputStream 相关的请求的所有数据已经读取完时 isFinished 方法返回 true。否则返回 false。
 * boolean isReady().
 *       如果可以无阻塞地读取数据 isReady 方法返回 true。如果没有数据可以无阻塞地读取该方法返回 false。如果isReady 方法返回 false，调用 read 方法是非法的，且必须抛出IllegalStateException。
 */
public class AppReadListener implements ReadListener {
    private AsyncContext asyncContext;

    public AppReadListener(AsyncContext context) {
        this.asyncContext = context;
    }

    @Override
    /**
     *  ServletInputStream 的方法 boolean isReady().如果可以无阻塞地读取数据 isReady 方法返回 true 否则返回 false
     * 当可以从 传入的请求流中 读取数据时 （socket正在进行流传输）
     * 当且仅当 下面描述的 ServletInputStream 的isReady 方法返回 false,容器随后将调用 onDataAvailable 方法。
     */
    public void onDataAvailable() throws IOException {
//        ServletResponse response = asyncContext.getResponse();
//        response.setCharacterEncoding("utf-8");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.write("<h1>start upload file</h1>");
//        printWriter.flush();

        ServletInputStream request = asyncContext.getRequest().getInputStream();
        //人工延长流处理时间 往后延长9秒终
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte buffer[] = new byte[1 * 1024];
        int readBytes = 0;
        while (request.isReady() && !request.isFinished()) {
            int length = request.read(buffer);
            if (length == -1 && request.isFinished()) {
                asyncContext.complete();
                System.out.println("Read: " + readBytes + " bytes");
                return;
            }
            readBytes += length;
        }
        System.out.println("onDataAvailable: request.isReady() - " + request.isReady());
    }

    /**
     * 当读取完所有传入的数据时调用这个方法 （socket流传输已经结束）
     *
     * @throws IOException
     */
    @Override
    public void onAllDataRead() throws IOException {
        //ServletInputStream 的方法 boolean isReady().如果可以无阻塞地读取数据 isReady 方法返回 true
        ServletRequest request = asyncContext.getRequest();
        ServletInputStream inputStream = request.getInputStream();

        System.out.println("onAllDataRead: request.isReady() - " + inputStream.isReady());
        ServletResponse response = asyncContext.getResponse();
        PrintWriter printWriter = response.getWriter();


        printWriter.write("<h1>upload file complete</h1>");
        printWriter.flush();
        // asyncContext.complete(); 只可以调用一次，调用了 就代表异步处理完成了
        asyncContext.complete();
    }

    @Override
    public void onError(Throwable throwable) {
        asyncContext.complete();
    }
}
