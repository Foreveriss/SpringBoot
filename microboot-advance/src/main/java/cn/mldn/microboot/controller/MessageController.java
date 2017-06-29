package cn.mldn.microboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.mldn.microboot.util.controller.AbstractBaseController;

@Controller
public class MessageController extends AbstractBaseController {
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(String mid, Model model) {
		model.addAttribute("url", "www.mldn.cn"); // request属性传递包装
		model.addAttribute("mid", mid); // request属性传递包装
		return "message/message_show"; // 此处只返回一个路径， 该路径没有设置后缀，后缀默认是*.html
	}
}








