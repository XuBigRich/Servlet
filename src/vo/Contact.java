package vo;

public class Contact {
	private int cid;
	private String cnam;
	private String sex;
	private String birth,birth2;
	private String tel;
	private int tid;
	private int sid;
	
	private Types tp;
	private Users us;
	
	public Contact(){
		tp=new Types();
		us=new Users();
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCnam() {
		return cnam;
	}

	public void setCnam(String cnam) {
		this.cnam = cnam;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public Types getTp() {
		return tp;
	}

	public void setTp(Types tp) {
		this.tp = tp;
	}

	public Users getUs() {
		return us;
	}

	public void setUs(Users us) {
		this.us = us;
	}

	public String getBirth2() {
		return birth2;
	}

	public void setBirth2(String birth2) {
		this.birth2 = birth2;
	}
}
