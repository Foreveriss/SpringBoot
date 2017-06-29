package cn.mldn.microboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.mldn.microboot.util.controller.AbstractBaseController;
import cn.mldn.microboot.vo.Member;

@Controller
public class MemberController extends AbstractBaseController {
	@RequestMapping(value = "/member/set", method = RequestMethod.GET)
	public String set(Model model) {
		Set<String> allNames = new HashSet<String>() ;
		List<Integer> allIds = new ArrayList<Integer>() ;
		for (int x = 0 ; x < 5 ; x ++) {
			allNames.add("mldn-" + x) ;
			allIds.add(x) ;
		}
		model.addAttribute("names", allNames) ;
		model.addAttribute("ids", allIds) ;
		model.addAttribute("mydate", new java.util.Date()) ;
		return "member/member_set" ;
	}
	
	@RequestMapping(value = "/member/map", method = RequestMethod.GET)
	public String map(Model model) {
		Map<String,Member> allMembers = new HashMap<String,Member>();
		for (int x = 0; x < 10; x++) {
			Member vo = new Member();
			vo.setMid(101L + x);
			vo.setName("啊三 - " + x);
			vo.setAge(9);
			vo.setSalary(99999.99);
			vo.setBirthday(new Date());
			allMembers.put("mldn-" + x, vo);
		}
		model.addAttribute("allMembers", allMembers);
		return "member/member_map";
	}
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Member> allMembers = new ArrayList<Member>();
		for (int x = 0; x < 10; x++) {
			Member vo = new Member();
			vo.setMid(101L + x);
			vo.setName("啊三 - " + x);
			vo.setAge(9);
			vo.setSalary(99999.99);
			vo.setBirthday(new Date());
			allMembers.add(vo) ;
		}
		model.addAttribute("allMembers", allMembers);
		return "member/member_list";
	}
}
