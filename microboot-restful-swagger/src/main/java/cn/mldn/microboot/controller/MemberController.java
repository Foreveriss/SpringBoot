package cn.mldn.microboot.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.mldn.microboot.vo.Member;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class MemberController {
	@ApiOperation(value = "实现人员信息的添加处理", notes = "就是加人的，多么的简单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "member", value = "用户描述的详细实体信息", required = true, dataType = "MemberClass")})
	@RequestMapping(value = "/member/add", method = RequestMethod.POST)
	public Object add(@RequestBody Member member) { // 表示当前的配置可以直接将参数变为VO对象
		System.err.println("【MemberController.add()接收对象】" + member);
		return true;
	}
	@ApiOperation(value = "获取指定编号的人员信息", notes = "只需要设置mid的信息就可以获取Member的完整内容")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mid", value = "用户编号", required = true, dataType = "String")})
	@RequestMapping(value = "/member/get/{mid}", method = RequestMethod.GET)
	public Member get(@PathVariable("mid") long mid) {
		Member vo = new Member();
		vo.setMid(mid);
		vo.setName("mldnjava - " + mid);
		vo.setBirthday(new Date());
		vo.setSalary(99999.99);
		vo.setAge(16);
		return vo;
	}
}
