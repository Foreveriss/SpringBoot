package cn.mldn.microboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping(value="/echo/{message}",method=RequestMethod.GET)
	public String echo(@PathVariable("message") String msg) {
		return "【ECHO】" + msg ;
	}
	@RequestMapping("/mul")
	public Object mul(int num) {
		return num * 2 ; 
	}
	
	@RequestMapping("/object")
	public String object(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("*** 客户端IP地址：" + request.getRemoteAddr());
		System.out.println("*** 取得客户端响应编码：" + response.getCharacterEncoding());
		System.out.println("*** 取得SessionID：" + request.getSession().getId());
		System.out.println("*** 取得真实路径：" + request.getServletContext().getRealPath("/upload/"));
		return "www.mldn.cn" ;
	}
	
	@RequestMapping("/")
	public String home() {
		return "www.mldn.cn";
	}
}
