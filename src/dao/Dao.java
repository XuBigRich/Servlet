<<<<<<< HEAD
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
	//É¾³ýÁªÏµÈË
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
	//Ìí¼ÓÁªÏµÈË
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
	//ÐÞ¸ÄÁªÏµÈË
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
	//¸ù¾ÝID²éÑ¯ÁªÏµÈË
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
	//²éÑ¯ËùÓÐÀà±ð
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
	//¶àÌõ¼þÈÎÒâ×éºÏÄ£ºý²éÑ¯
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
	//²éÑ¯µ±Ç°µÇÂ¼ÕßµÄËùÓÐÁªÏµÈË²¢·ÖÒ³
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
	//µÇÂ¼
	public Users login(Users u){
		Users us=new Users();
		Connection con=ConFactory.getCon();
		String sql="select * from users where nam=? and pwd=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,u.getNam());
			ps.setString(2,u.getPwd());
			ResultSet rs=ps.executeQuery();//ÒªÃ´ÓÐ¼ÇÂ¼£¬ÒªÃ´Ã»¼ÇÂ¼
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
=======
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
	//åˆ é™¤è”ç³»äºº
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
	//æ·»åŠ è”ç³»äºº
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
	//ä¿®æ”¹è”ç³»äºº
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
	//æ ¹æ®IDæŸ¥è¯¢è”ç³»äºº
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
	//æŸ¥è¯¢æ‰€æœ‰ç±»åˆ«
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
	//å¤šæ¡ä»¶ä»»æ„ç»„åˆæ¨¡ç³ŠæŸ¥è¯¢
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
	//æŸ¥è¯¢å½“å‰ç™»å½•è€…çš„æ‰€æœ‰è”ç³»äººå¹¶åˆ†é¡µ
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
	//ç™»å½•
	public Users login(Users u){
		Users us=new Users();
		Connection con=ConFactory.getCon();
		String sql="select * from users where nam=? and pwd=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,u.getNam());
			ps.setString(2,u.getPwd());
			ResultSet rs=ps.executeQuery();//è¦ä¹ˆæœ‰è®°å½•ï¼Œè¦ä¹ˆæ²¡è®°å½•
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
>>>>>>> f755fd64db48b127135e88fd8ca6f213464fbd42
