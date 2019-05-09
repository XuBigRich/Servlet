package tools;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	private int pagesize;//ÿҳ��ʾ�ļ�¼����
	private int count;//��¼������
	private int total;//��ҳ��
	private int p;//��ǰҳ��
	private List data;//���ݼ���
	
	public PageBean(){
		pagesize=5;//Ĭ��ÿҳ��ʾ5����¼
		data=new ArrayList();
	}

	public int getPagesize() {
		return pagesize;
	}
	//����ÿҳ��ʾ�����������ȵ��ã�
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getCount() {
		return count;
	}
	//���ü�¼����������ε��ã�
	public void setCount(int count) {
		this.count = count;
		total=(int)(Math.ceil(count*1.0/pagesize));
	}

	public int getTotal() {
		return total;
	}
	
	public int getP() {
		return p;
	}
	//���ô���ʾ��ҳ�룬��������ҳ���ύ������ҳ�루�����ã�
	public void setP(int p) {
		if(p<1)this.p=1;
		else if(p>total)this.p=total;
		else this.p = p;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
}
