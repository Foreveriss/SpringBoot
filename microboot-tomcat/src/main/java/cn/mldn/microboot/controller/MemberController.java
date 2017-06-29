package cn.mldn.microboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mldn.microboot.util.controller.AbstractBaseController;
import cn.mldn.microboot.vo.Member;

@Controller
public class MemberController extends AbstractBaseController {
	@RequestMapping(value="/")
	@ResponseBody
	public String get() {
		return "hello world" ;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(Member vo) { // 增加前的准备操作路径
		return vo;
	}
	@RequestMapping(value = "/addPre", method = RequestMethod.GET)
	public String addPre() { // 增加前的准备操作路径
		return "member_add";
	}
}
