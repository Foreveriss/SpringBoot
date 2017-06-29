package cn.mldn.microboot.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Member implements Serializable {
	private String mid;
	private Integer age;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Member [mid=" + mid + ", age=" + age + "]";
	}
}
