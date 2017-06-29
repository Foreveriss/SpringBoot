package cn.mldn.microboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@RequestMapping("/loginPage")
	public String get() {
		return "member_login" ;
	}
}
