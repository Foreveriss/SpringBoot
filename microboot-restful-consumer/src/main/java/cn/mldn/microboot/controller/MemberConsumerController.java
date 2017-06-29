package cn.mldn.microboot.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import cn.mldn.microboot.util.controller.AbstractBaseController;
import cn.mldn.microboot.vo.Member;

@Controller
public class MemberConsumerController extends AbstractBaseController {
	@Resource
	private RestTemplate restTemplate;
	@RequestMapping(value = "/consumer/get", method = RequestMethod.GET)
	public String getMember(long mid,Model model) {
		Member member = this.restTemplate.getForObject(
				"http://localhost:8080/member/get/" + mid, Member.class);
		model.addAttribute("member", member) ;
		return "member_show";
	}
	@RequestMapping(value = "/consumer/add", method = RequestMethod.GET)
	@ResponseBody
	public Object addMember(Member member) {
		Boolean flag = this.restTemplate.postForObject(
				"http://localhost:8080/member/add", member, Boolean.class);
		return flag;
	}
}
