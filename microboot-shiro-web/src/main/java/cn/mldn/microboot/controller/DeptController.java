package cn.mldn.microboot.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {
	@RequiresAuthentication
	@RequestMapping("/pages/back/dept/get")
	@ResponseBody
	public String get() {
		return "部门信息" ;
	}
	@RequestMapping("/pages/back/dept/show")
	public String show() {
		return "dept_show" ;
	}
}
