package cn.mldn.microboot.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.mldn.microboot.util.controller.AbstractBaseController;
import cn.mldn.microboot.vo.Member;

@Controller
public class MessageController extends AbstractBaseController {
	@RequestMapping(value = "/message/member_show", method = RequestMethod.GET)
	public String memberShow(Model model) {
		Member vo = new Member();
		vo.setMid(101L);
		vo.setName("啊三");
		vo.setAge(9);
		vo.setSalary(99999.99);
		vo.setBirthday(new Date());
		model.addAttribute("member", vo);
		return "message/member_show";
	}

	@RequestMapping(value = "/message/inner", method = RequestMethod.GET)
	public String inner(HttpServletRequest request, Model model) {
		request.setAttribute("requestMessage", "mldnjava-request");
		request.getSession().setAttribute("sessionMessage", "mldnjava-session");
		request.getServletContext().setAttribute("applicationMessage",
				"mldnjava-application");
		model.addAttribute("url", "www.mldn.cn");
		request.setAttribute("url2",
				"<span style='color:red'>www.mldn.cn</span>");
		return "message/message_show_inner";
	}

	@RequestMapping(value = "/message/showStyle", method = RequestMethod.GET)
	public String showStyle(Model model) { // 通过model可以实现内容的传递
		model.addAttribute("url", "<span style='color:red'>www.mldn.cn</span>"); // request属性传递包装
		return "message/message_show_style";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(String mid, Model model) { // 通过model可以实现内容的传递
		model.addAttribute("url", "www.mldn.cn"); // request属性传递包装
		model.addAttribute("mid", mid); // request属性传递包装
		return "message/message_show"; // 此处只返回一个路径， 该路径没有设置后缀，后缀默认是*.html
	}
}
