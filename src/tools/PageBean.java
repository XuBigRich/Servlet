package tools;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	private int pagesize;//每页显示的记录条数
	private int count;//记录总条数
	private int total;//总页数
	private int p;//当前页码
	private List data;//数据集合
	
	public PageBean(){
		pagesize=5;//默认每页显示5条记录
		data=new ArrayList();
	}

	public int getPagesize() {
		return pagesize;
	}
	//设置每页显示的条数（最先调用）
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getCount() {
		return count;
	}
	//设置记录总条数（其次调用）
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
	//设置待显示的页码，参数：从页面提交过来的页码（最后调用）
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
