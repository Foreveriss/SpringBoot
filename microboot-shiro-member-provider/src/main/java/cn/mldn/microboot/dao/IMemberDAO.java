package cn.mldn.microboot.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.mldn.vo.Member;

@Mapper 
public interface IMemberDAO {
	public Member findById(String mid) ;
}
