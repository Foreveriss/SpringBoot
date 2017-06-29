package cn.mldn.microboot.service.impl;

import org.springframework.stereotype.Service;

import cn.mldn.microboot.service.IMemberService;
import cn.mldn.microboot.vo.Member;

@Service
public class MemberServiceImpl implements IMemberService {
	@Override
	public Member get(long mid) {
		Member vo = new Member();
		vo.setMid(mid);
		vo.setName("KING");
		vo.setSalary(50000.00);
		return vo;
	}

}
