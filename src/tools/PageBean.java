package tools;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	private int pagesize;//ÿҳ��ʾ�ļ�¼����
	private int count;//��¼������
	private int total;//��ҳ��
	private int p;//��ǰҳ��
	private List data;//���ݼ���



	private List show; //Ҫ��ʾ�� �б�
	
	public PageBean(){
		pagesize=5;//Ĭ��ÿҳ��ʾ5����¼
		data=new ArrayList();
		show=new ArrayList();
	}

	//���ü�¼����������ε��ã�
	public void setCount(int count) {
		this.count = count;  //һ���ж�������¼
		total=(int)(Math.ceil(count*1.0/pagesize));   //һ������ҳ
	}
	//���ô���ʾ��ҳ�룬��������ҳ���ύ������ҳ�루�����ã�
	public void setP(int p) {
		if(p<1)this.p=1;
		else if(p>total)this.p=total;
		else this.p = p;
		setShow(this.p);
	}
	//����ÿҳ��ʾ�����������ȵ��ã�
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotal() {
		return total;
	}

	public int getP() {
		return p;
	}
	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
	public int getPagesize() {
		return pagesize;
	}
	public int getCount() {
		return count;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List getShow() {
		return show;
	}

	public void setShow(int p) {
		int c=p*pagesize-pagesize;
		for(int i=0;i<pagesize;i++){
			if(c>data.size()-1){
				break;
			}
			Object user=data.get(c);
			c++;
			show.add(user);
		}
	}
}
