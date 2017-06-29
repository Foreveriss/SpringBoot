package cn.mldn.microboot.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.microboot.dao.IActionDAO;
import cn.mldn.microboot.dao.IMemberDAO;
import cn.mldn.microboot.dao.IRoleDAO;
import cn.mldn.microboot.service.IMemberService;
import cn.mldn.vo.Member;
@Service
public class MemberServiceImpl implements IMemberService {
	@Resource
	private IMemberDAO memberDAO;
	@Resource
	private IRoleDAO roleDAO;
	@Resource
	private IActionDAO actionDAO;
	@Override
	public Member get(String mid) {
		return this.memberDAO.findById(mid);
	}

	@Override
	public Map<String, Set<String>> listAuthByMember(String mid) {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		map.put("allRoles", this.roleDAO.findAllRoleByMember(mid));
		map.put("allActions", this.actionDAO.findAllActionByMember(mid));
		return map;
	}

}
