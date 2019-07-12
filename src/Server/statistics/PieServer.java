package Server.statistics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PieServer {
	public static void paint(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			//��״ͼ
//				DefaultPieDataset ds=new DefaultPieDataset();
//				ds.setValue("ƻ��", 20);
//				ds.setValue("����", 40);
//				ds.setValue("����", 12);
//				ds.setValue("����", 18);
//				ds.setValue("����", 10);
//
//				JFreeChart fc=ChartFactory.createPieChart("ͼʾ�ı���",ds, true, true, false);


			//ֱ��ͼ
//			 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
//		        dataset.setValue(10,"a","������Ա");
//		        dataset.setValue(20,"b","�г���Ա");
//		        dataset.setValue(40,"c","������Ա");
//		        dataset.setValue(15,"d","������Ա");
//
//		        JFreeChart fc=ChartFactory.createBarChart("hi", "��Ա�ֲ�", "��Ա����", dataset, PlotOrientation.VERTICAL, true, true, false); //����һ��JFreeChart
//		        fc.setTitle("ĳ��˾��֯�ṹͼ");//�����������ñ��⣬�滻��hi������


			//����ͼ
			XYSeries xy1 = new XYSeries("��������");
			XYSeries xy2 = new XYSeries("PHֵ");
			XYSeries xy3 = new XYSeries("������");
			XYSeries xy4 = new XYSeries("����");

			xy1.add(1, 39);
			xy2.add(1, 6);
			xy3.add(1, 0);
			xy4.add(1, 2);

			xy1.add(2, 78);
			xy2.add(2, 9);
			xy3.add(2, 22);
			xy4.add(2, 300);

			xy1.add(3, 15);
			xy2.add(3, 3);
			xy3.add(3, 69);
			xy4.add(3, 201);

			XYSeriesCollection ds = new XYSeriesCollection();
			ds.addSeries(xy1);
			ds.addSeries(xy2);
			ds.addSeries(xy3);
			ds.addSeries(xy4);

			JFreeChart fc = ChartFactory.createXYLineChart("����ͼ", "����", "����", ds, PlotOrientation.VERTICAL, true, true, false);


			BufferedImage img = fc.createBufferedImage(600, 400);

//				BufferedImage img=new BufferedImage(80,50,BufferedImage.TYPE_INT_RGB);
//				Graphics2D g2=img.createGraphics();
//				g2.setColor(Color.BLUE);
//				g2.fillRect(0, 0, 80,50);
//				g2.setColor(Color.WHITE);
//				g2.setFont(new Font("����",Font.BOLD,24));
//				int rnd=(int)(Math.random()*8999+1000);
//				req.getSession().setAttribute("rnd",rnd);
//				g2.drawString(rnd+"",25,25);//1000-9999
//
//				for(int i=0;i<20;i++){
//					int x1=(int)(Math.random()*80);
//					int y1=(int)(Math.random()*50);
//					int x2=(int)(Math.random()*80);
//					int y2=(int)(Math.random()*50);
//					int r=(int)(Math.random()*255);
//					int g=(int)(Math.random()*255);
//					int b=(int)(Math.random()*255);
//					g2.setColor(new Color(r,g,b));
//					float w=(float)(Math.random()*3);
//					g2.setStroke(new BasicStroke(w));
//					g2.drawLine(x1, y1, x2, y2);
//				}

			res.setContentType("image/png;charset=utf-8");
			ImageIO.write(img, "png", res.getOutputStream());//����ҳ���ͼƬ
			/*��ǰ�˴�ͼƬ����һ���󵨵��뷨����BufferedImage ת��ΪBase64 */
//			req.setAttribute("png",img);
//			req.getRequestDispatcher("showimg.jsp").forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}