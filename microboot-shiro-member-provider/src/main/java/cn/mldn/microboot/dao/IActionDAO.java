package cn.mldn.microboot.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IActionDAO {
	public Set<String> findAllActionByMember(String mid) ; 
}
