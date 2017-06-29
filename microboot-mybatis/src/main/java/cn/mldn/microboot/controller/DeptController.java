package cn.mldn.microboot.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.mldn.microboot.service.IDeptService;
import cn.mldn.microboot.util.controller.AbstractBaseController;

@RestController
public class DeptController extends AbstractBaseController {
	@Resource
	private IDeptService deptService ;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() { // 通过model可以实现内容的传递
		return this.deptService.list() ; 
	}
}
