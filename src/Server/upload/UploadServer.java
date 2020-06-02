package Server.upload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName uploadServer.java
 * @Description TODO
 * @createTime 2019年05月06日 18:43:00
 */
public class UploadServer {
    //上传
    public static void uploadServletfindContact(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //存储路径
        String savePath=request.getServletContext().getRealPath("/WEB-INF/uploadFile");
        //获取上传的文件集合
        Collection<Part> parts = request.getParts();
        if(parts.size()==1){
            /*Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。*/
            //Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
            //Part part = parts[0];//从上传的文件集合中获取Part对象
            System.out.println("=====================================输出request的请求头");
            Enumeration HeaderName=request.getHeaderNames();
            while(HeaderName.hasMoreElements()){
                System.out.println(HeaderName.nextElement());
            }
            System.out.println("=====================================");
            System.out.println("输出Part（name为file的）（request请求的请求体）的请求头");
            System.out.println("=====================================");
            Part part=request.getPart("file");//通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
            Collection<String> nameHeader=part.getHeaderNames();
            Iterator iterator=nameHeader.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            System.out.println("=====================================");
            System.out.println("输出request请求体的请求头为content-disposition的 内容");
            System.out.println("=====================================");
            //Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
            //获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
            String header = part.getHeader("content-disposition");
            //获取文件名
            String fileName = getFileName(header);
            part.write(savePath+File.separator+fileName);
        }else {
            System.out.println("=====================================");
            System.out.println("Part的所有请求头");
            System.out.println("=====================================");
            Collection<Part> parts1=request.getParts();
            for(Part part:parts1){
                System.out.println(part.getName());
                if(part.getName().equals("file")){
                    String header = part.getHeader("content-disposition");
                    //获取文件名
                    String fileName = getFileName(header);
                    part.write(savePath+File.separator+fileName);
                }
            }
        }
    }
    /*
     *  根据请求头解析出文件名
     * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="snmp4j--api.zip"
     *                 IE浏览器下：form-data; name="file"; filename="E:\snmp4j--api.zip"
     * @param header 请求头
     * @return 文件名
     **/
    public static String getFileName(String header){
        System.out.println("=====================================");
        System.out.println(header);
        /*
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String [] tempArr1=header.split(";");
        /*
         *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
         *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");

       /* //此方法也行s
            for(String name:tempArr1){
            if(name.contains("filename")){
                return name.split("=")[1];
            }
        }*/
        return fileName;
    }
}
