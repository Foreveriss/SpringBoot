package cn.mldn.microboot.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.mldn.microboot.vo.Dept;

public interface IDeptService {
	@Transactional(readOnly = true)
	public List<Dept> list() ;
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean add(Dept vo) ;
}
