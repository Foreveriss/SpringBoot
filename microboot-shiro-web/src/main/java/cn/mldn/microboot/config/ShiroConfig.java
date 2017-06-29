package cn.mldn.microboot.config;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.mldn.microboot.cache.RedisCacheManager;
import cn.mldn.microboot.realm.CustomerCredentialsMatcher;
import cn.mldn.microboot.realm.MemberRealm;
import cn.mldn.microboot.session.RedisSessionDAO;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroDialect getShiroDialect() {	// 必须配置此操作才可以使用thymeleaf-extras-shiro开发包
		return new ShiroDialect() ;
	}
	
	@Bean
	public MemberRealm getRealm() {// 1、获取配置的Realm，之所以没使用注解配置，是因为此处需要考虑到加密处理
		MemberRealm realm = new MemberRealm();
		realm.setCredentialsMatcher(new CustomerCredentialsMatcher());	
		return realm;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

//	@Bean
//	public EhCacheManager getCacheManager() {// 2、缓存配置
//		EhCacheManager cacheManager = new EhCacheManager();
//		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
//		return cacheManager;
//	}

	@Bean
	public SessionIdGenerator getSessionIdGenerator() { // 3
		return new JavaUuidSessionIdGenerator();
	}

	@Bean
	public SessionDAO getSessionDAO(SessionIdGenerator sessionIdGenerator) { // 4
		RedisSessionDAO sessionDAO = new RedisSessionDAO();	// 使用Redis进行Session管理
		sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		sessionDAO.setSessionIdGenerator(sessionIdGenerator);
		return sessionDAO;
	}

	@Bean
	public RememberMeManager getRememberManager() { // 5
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		SimpleCookie cookie = new SimpleCookie("MLDNJAVA-RememberMe");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(3600);
		rememberMeManager.setCookie(cookie);
		return rememberMeManager;
	}

	@Bean
	public QuartzSessionValidationScheduler getQuartzSessionValidationScheduler() {
		QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
		sessionValidationScheduler.setSessionValidationInterval(100000);
		return sessionValidationScheduler;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}

	@Bean
	public DefaultWebSessionManager getSessionManager(SessionDAO sessionDAO,
			QuartzSessionValidationScheduler sessionValidationScheduler) { // 6
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1000000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationScheduler(sessionValidationScheduler);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionDAO(sessionDAO);
		SimpleCookie sessionIdCookie = new SimpleCookie("mldn-session-id");
		sessionIdCookie.setHttpOnly(true);
		sessionIdCookie.setMaxAge(-1);
		sessionManager.setSessionIdCookie(sessionIdCookie);
		sessionManager.setSessionIdCookieEnabled(true);
		return sessionManager;
	}

	@Bean
	public DefaultWebSecurityManager getSecurityManager(Realm memberRealm, RedisCacheManager cacheManager,
			SessionManager sessionManager, RememberMeManager rememberMeManager) {// 7
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(memberRealm);
		securityManager.setCacheManager(cacheManager);
		securityManager.setSessionManager(sessionManager);
		securityManager.setRememberMeManager(rememberMeManager);
		return securityManager;
	}

	public FormAuthenticationFilter getLoginFilter() { // 在ShiroFilterFactoryBean中使用
		FormAuthenticationFilter filter = new FormAuthenticationFilter();
		filter.setUsernameParam("mid");
		filter.setPasswordParam("password");
		filter.setRememberMeParam("rememberMe");
		filter.setLoginUrl("/loginPage");	// 登录提交页面
		filter.setFailureKeyAttribute("error");
		return filter;
	}

	public LogoutFilter getLogoutFilter() { // 在ShiroFilterFactoryBean中使用
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/");	// 首页路径，登录注销后回到的页面
		return logoutFilter;
	}

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/loginPage");	// 设置登录页路径
		shiroFilterFactoryBean.setSuccessUrl("/pages/hello");	// 设置跳转成功页
		shiroFilterFactoryBean.setUnauthorizedUrl("/pages/unauthUrl");	// 授权错误页
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("authc", this.getLoginFilter());
		filters.put("logout", this.getLogoutFilter());
		shiroFilterFactoryBean.setFilters(filters);
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/loginPage", "authc");	// 定义内置登录处理
		filterChainDefinitionMap.put("/pages/back/**", "authc");
		filterChainDefinitionMap.put("/*", "anon");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
}
