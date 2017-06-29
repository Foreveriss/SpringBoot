package cn.mldn.microboot.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Member implements Serializable {
	private Long mid ;
	private String name ;
	private Integer age ;
	private Double salary ;
	private Date birthday ;
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Member [mid=" + mid + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + ", birthday=" + birthday + "]";
	}
}
