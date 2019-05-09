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
 * @createTime 2019��05��06�� 18:43:00
 */
public class UploadServer {
    //�ϴ�
    public static void uploadServletfindContact(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //�洢·��
        String savePath=request.getServletContext().getRealPath("/WEB-INF/uploadFile");
        //��ȡ�ϴ����ļ�����
        Collection<Part> parts = request.getParts();
        if(parts.size()==1){
            /*Servlet3.0��multipart/form-data��POST�����װ��Part��ͨ��Part���ϴ����ļ����в�����*/
            //Servlet3.0��multipart/form-data��POST�����װ��Part��ͨ��Part���ϴ����ļ����в�����
            //Part part = parts[0];//���ϴ����ļ������л�ȡPart����
            System.out.println("=====================================���request������ͷ");
            Enumeration HeaderName=request.getHeaderNames();
            while(HeaderName.hasMoreElements()){
                System.out.println(HeaderName.nextElement());
            }
            System.out.println("=====================================");
            System.out.println("���Part��nameΪfile�ģ���request����������壩������ͷ");
            System.out.println("=====================================");
            Part part=request.getPart("file");//ͨ����file�ؼ�(<input type="file" name="file">)������ֱ�ӻ�ȡPart����
            Collection<String> nameHeader=part.getHeaderNames();
            Iterator iterator=nameHeader.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            System.out.println("=====================================");
            System.out.println("���request�����������ͷΪcontent-disposition�� ����");
            System.out.println("=====================================");
            //Servlet3û���ṩֱ�ӻ�ȡ�ļ����ķ���,��Ҫ������ͷ�н�������
            //��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
            String header = part.getHeader("content-disposition");
            //��ȡ�ļ���
            String fileName = getFileName(header);
            part.write(savePath+File.separator+fileName);
        }else {
            System.out.println("=====================================");
            System.out.println("Part����������ͷ");
            System.out.println("=====================================");
            Collection<Part> parts1=request.getParts();
            for(Part part:parts1){
                System.out.println(part.getName());
                if(part.getName().equals("file")){
                    String header = part.getHeader("content-disposition");
                    //��ȡ�ļ���
                    String fileName = getFileName(header);
                    part.write(savePath+File.separator+fileName);
                }
            }
        }
    }
    /*
     *  ��������ͷ�������ļ���
     * ����ͷ�ĸ�ʽ�������google������£�form-data; name="file"; filename="snmp4j--api.zip"
     *                 IE������£�form-data; name="file"; filename="E:\snmp4j--api.zip"
     * @param header ����ͷ
     * @return �ļ���
     **/
    public static String getFileName(String header){
        System.out.println("=====================================");
        System.out.println(header);
        /*
         * String[] tempArr1 = header.split(";");����ִ����֮���ڲ�ͬ��������£�tempArr1���������������������
         * �������google������£�tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE������£�tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String [] tempArr1=header.split(";");
        /*
         *�������google������£�tempArr2={filename,"snmp4j--api.zip"}
         *IE������£�tempArr2={filename,"E:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        //��ȡ�ļ��������ݸ����������д��
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");

       /* //�˷���Ҳ��s
            for(String name:tempArr1){
            if(name.contains("filename")){
                return name.split("=")[1];
            }
        }*/
        return fileName;
    }
}
