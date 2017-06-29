package cn.mldn.microboot.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.mldn.microboot.service.IMemberService;

@RestController
public class MemberController {
	@Resource
	private IMemberService memberService;
	@RequestMapping(value="/member/get",method=RequestMethod.POST)
	public Object get(String mid) {
		return this.memberService.get(mid) ;
	}
	@RequestMapping(value="/member/auth",method=RequestMethod.POST)
	public Object auth(String mid) {
		return this.memberService.listAuthByMember(mid) ;
	}
}
