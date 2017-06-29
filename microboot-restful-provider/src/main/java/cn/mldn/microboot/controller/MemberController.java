package cn.mldn.microboot.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.mldn.microboot.vo.Member;

@RestController
public class MemberController {
	@RequestMapping(value = "/member/add",method=RequestMethod.POST)
	public Object add(@RequestBody Member member) {	// 表示当前的配置可以直接将参数变为VO对象
		System.err.println("【MemberController.add()接收对象】" + member);
		return true;
	}
	@RequestMapping(value = "/member/get/{mid}",method=RequestMethod.GET)
	public Member get(@PathVariable("mid") long mid) {
		Member vo = new Member();
		vo.setMid(mid);
		vo.setName("mldnjava - " + mid);
		vo.setBirthday(new Date());
		vo.setSalary(99999.99);
		vo.setAge(16);
		return vo;
	}
}
