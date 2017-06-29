package cn.mldn.util.enctype;

import java.util.Base64;

public class PasswordUtil {
	private static final String SEED  = "mldnjava" ;	// 该数据为种子数，如果要加密则需要使用Base64做多次迭代
	private static final int NE_NUM = 3 ;	// 密码迭代处理3次
	private PasswordUtil() {}
	private static String createSeed() {	// 创建一个基于Base64的种子数
		String str = SEED ;
		for (int x = 0 ; x < NE_NUM ; x ++) {
			str = Base64.getEncoder().encodeToString(str.getBytes()) ;
		}
		return str ;
	}
	/**
	 * 进行密码的处理操作
	 * @param password 用户输入的真实密码
	 * @return 与数据库保存匹配的加密的处理密码
	 */
	public static String getPassword(String password) {
		MD5Code md5 = new MD5Code() ;
		String pass = "{" + password + ":" + createSeed() + "}";
		for (int x = 0 ; x < NE_NUM ; x ++) {
			pass = md5.getMD5ofStr(pass) ;
		}
		return pass ; 
	}
}
