package cn.mldn.microboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.mldn.microboot.vo.Dept;
@Mapper
public interface IDeptDAO {
	public List<Dept> findAll();
	public boolean doCreate(Dept vo) ;
}
