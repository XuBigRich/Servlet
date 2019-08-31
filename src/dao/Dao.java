package dao;

import vo.Contact;
import vo.Types;
import vo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	//删除联系人
	public void delContact(int cid){
		Connection con=ConFactory.getCon();
		String sql="delete contact where cid="+cid;
		try {
			con.createStatement().execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConFactory.close(con);
		}
	}
	//添加联系人
	public void addContact(Contact c){
		Connection con=ConFactory.getCon();
		String sql="insert into contact values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,c.getCnam());
			ps.setString(2,c.getSex());
			ps.setString(3,c.getBirth());
			ps.setString(4,c.getTel());
			ps.setInt(5,c.getTid());
			ps.setInt(6,c.getSid());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConFactory.close(con);
		}
	}
	//修改联系人
	public void updateContact(Contact c){
		Connection con=ConFactory.getCon();
		String sql="update contact set cnam=?,sex=?,birth=?,tel=?,tid=? where cid=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,c.getCnam());
			ps.setString(2,c.getSex());
			ps.setString(3,c.getBirth());
			ps.setString(4,c.getTel());
			ps.setInt(5,c.getTid());
			ps.setInt(6,c.getCid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConFactory.close(con);
		}
	}
	//根据ID查询联系人
	public Contact findContact(int cid){
		Contact c=new Contact();
		Connection con=ConFactory.getCon();
		String sql="select * from contact where cid="+cid;
		try {
			ResultSet rs=con.createStatement().executeQuery(sql);
			rs.next();
			c.setCid(rs.getInt("cid"));
			c.setCnam(rs.getString("cnam"));
			c.setSex(rs.getString("sex"));
			c.setBirth(rs.getString("birth"));
			c.setTel(rs.getString("tel"));
			c.setTid(rs.getInt("tid"));
			c.setSid(rs.getInt("sid"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConFactory.close(con);
		}
		return c;
	}
	//查询所有类别
	public List allTypes(){
		List ts=new ArrayList();
		Connection con=ConFactory.getCon();
		String sql="select * from types";
		try {
			ResultSet rs=con.createStatement().executeQuery(sql);
			while(rs.next()){
				Types t=new Types();
				t.setTid(rs.getInt("tid"));
				t.setTnam(rs.getString("tnam"));
				ts.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConFactory.close(con);
		}
		return ts;
	}
	//多条件任意组合模糊查询
	public List findContact(Contact c){
		List cs=new ArrayList();
		Connection con=ConFactory.getCon();
		String sql="select c.cid,c.cnam,c.sex,c.birth,c.tel,t.tnam ";
		sql+="from contact c,types t,users u ";
		sql+="where c.tid=t.tid and c.sid=u.sid and c.sid="+c.getSid();
		if(c!=null){
			if(c.getCnam()!=null&&!c.getCnam().equals("")){
				sql+=" and c.cnam like '%"+c.getCnam()+"%' ";
			}
			if(!c.getSex().equals("a")){
				sql+=" and c.sex='"+c.getSex()+"' ";
			}
			if(c.getBirth()!=null&&c.getBirth2()!=null&&!c.getBirth().equals("")&&!c.getBirth2().equals("")){
				sql+=" and c.birth between '"+c.getBirth()+"' and '"+c.getBirth2()+"' ";
			}
			if(c.getTid()!=0){
				sql+=" and c.tid="+c.getTid();
			}
		}
		try {
			ResultSet rs=con.createStatement().executeQuery(sql);
			while(rs.next()){
				Contact ct=new Contact();
				ct.setCid(rs.getInt("cid"));
				ct.setCnam(rs.getString("cnam"));
				ct.setSex(rs.getString("sex"));
				ct.setBirth(rs.getString("birth"));
				ct.setTel(rs.getString("tel"));
				ct.getTp().setTnam(rs.getString("tnam"));
				cs.add(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConFactory.close(con);
		}
		return cs;
	}
	//查询当前登录者的所有联系人并分页
	public List allContact(int sid){
		List cs=new ArrayList();
		Connection con=ConFactory.getCon();
		String sql="select c.cid,c.cnam,c.sex,c.birth,c.tel,t.tnam ";
		sql+="from contact c,types t,users u ";
		sql+="where c.tid=t.tid and c.sid=u.sid and c.sid="+sid;
		try {
			ResultSet rs=con.createStatement().executeQuery(sql);
			while(rs.next()){
				Contact ct=new Contact();
				ct.setCid(rs.getInt("cid"));
				ct.setCnam(rs.getString("cnam"));
				ct.setSex(rs.getString("sex"));
				ct.setBirth(rs.getString("birth"));
				ct.setTel(rs.getString("tel"));
				ct.getTp().setTnam(rs.getString("tnam"));
				cs.add(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConFactory.close(con);
		}
		return cs;
	}
	//登录
	public Users login(Users u){
		Users us=new Users();
		Connection con=ConFactory.getCon();
		String sql="select * from users where nam=? and pwd=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,u.getNam());
			ps.setString(2,u.getPwd());
			ResultSet rs=ps.executeQuery();//要么有记录，要么没记录
			rs.next();
			us.setSid(rs.getInt("sid"));
			us.setNam(rs.getString("nam"));
		} catch (Exception e) {
			us=null;
		} finally {
			ConFactory.close(con);
		}
		return us;
	}
}