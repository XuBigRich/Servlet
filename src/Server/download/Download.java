package Server.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Download.java
 * @Description TODO
 * @createTime 2019年05月09日 10:36:00
 */

/*
* 关于下载名 乱码
* 方法1：
response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
下载的程序里有了上面一句，一般在IE6的下载提示框上将正确显示文件的名字，无论是简体中文，还是日文。但是文字只要
超过17个字，就不能下载了。
一. 通过原来的方式，也就是先用URLEncoder编码，当中文文字超过17个时，IE6 无法下载文件。这是IE的bug，参见微软的知识库文章 KB816868 。原因可能是IE在处理 Response Header 的时候，对header的长度限制在150字节左右。
而一个汉字编码成UTF-8是9个字节，那么17个字便是153个字节，所以会报错。而且不跟后缀也不对.
方法2：
response.setHeader( "Content-Disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) );
在确保附件文件名都是简 体中文字的情况下，那么这个办法确实是最有效的，不用让客户逐个的升级IE。如果台湾同胞用，把gb2312改成big5就行。但现在的系统通常都加入了 国际化的支持，普遍使用UTF-8。如果文件名中又有简体中文字，
又有繁体中文，还有日文。那么乱码便产生了。另外，在上Firefox (v1.0-en)下载也是乱码。
* */
public class Download {
    //获取全部文件名
  public static void allFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String path=request.getSession().getServletContext().getRealPath("/WEB-INF/uploadFile");
      File file= new File(path);
      File[] files=file.listFiles();
      List<String> fileName=new ArrayList();
      for(File file1:files){
          fileName.add(file1.getName());
      }
      request.setAttribute("fnames",fileName);
      request.getRequestDispatcher("filelist.jsp").forward(request, response);
  }
  public static void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name=request.getParameter("fn");
      String path=request.getSession().getServletContext().getRealPath("/WEB-INF/uploadFile/"+name);
      FileInputStream fileInputStream=new FileInputStream(path);
      byte[] bytes=new byte[1024];
      //这个编码一定要设置 要不然 下载的时候 文件名 乱码
      response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode(name, "UTF-8"));
      ServletOutputStream out = response.getOutputStream();
      while(fileInputStream.read(bytes)!=-1){
          out.write(bytes);
      }
      out.close();
    }
}
