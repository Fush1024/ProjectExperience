package com.quickly.devploment.pojo;

/**
 * @ClassName StudentPojo
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/26 11:06
 * @Version V-1.0
 **/
public class StudentPojo {
	private int s_id;
	private int sno;
	private String sname;
	private int sage;
	private String ssex;
	private int father_id;
	private int mather_id;
	private String note;

	@Override
	public String toString() {
		return "StudentPojo{" + "s_id=" + s_id + ", sno=" + sno + ", sname='" + sname + '\'' + ", sage=" + sage
				+ ", ssex='" + ssex + '\'' + ", father_id=" + father_id + ", mather_id=" + mather_id + ", note='" + note
				+ '\'' + '}';
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getSage() {
		return sage;
	}

	public void setSage(int sage) {
		this.sage = sage;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public int getFather_id() {
		return father_id;
	}

	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}

	public int getMather_id() {
		return mather_id;
	}

	public void setMather_id(int mather_id) {
		this.mather_id = mather_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public StudentPojo() {
	}

	public StudentPojo(int s_id, int sno, String sname, int sage, String ssex, int father_id, int mather_id,
			String note) {
		this.s_id = s_id;
		this.sno = sno;
		this.sname = sname;
		this.sage = sage;
		this.ssex = ssex;
		this.father_id = father_id;
		this.mather_id = mather_id;
		this.note = note;
	}
}
