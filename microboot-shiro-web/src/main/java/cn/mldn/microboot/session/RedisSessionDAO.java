package cn.mldn.microboot.session;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
// 此时的类将实现SessionDAO的改写
import org.springframework.data.redis.core.RedisTemplate;
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {
	private Log log = LogFactory.getLog(RedisSessionDAO.class);
	@Resource
	private RedisTemplate<String, Object> redisTempate; // 要提供有Redis处理工具类
	@Override
	protected Serializable doCreate(Session session) { // 创建Session，返回session id
		log.info("*** doCreate : " + session);
		Serializable sessionId = super.doCreate(session); // 创建sessionid
		// 将当前创建好的Session的数据保存在Redis数据库里面
		this.redisTempate.opsForValue().set(sessionId.toString(), session,
				1800);
		return sessionId;
	}
	@Override
	protected Session doReadSession(Serializable sessionId) { // 根据session
																// id读取session数据
		log.info("*** doReadSession : " + sessionId);
		Session session = super.doReadSession(sessionId); // 读取Session数据
		if (session == null) { // 现在没有读取到session数据，通过Redis读取
			return (Session) this.redisTempate.opsForValue()
					.get(sessionId.toString());
		}
		return null;
	}
	@Override
	protected void doUpdate(Session session) { // 实现Session更新，每次操作都要更新
		log.info("*** doUpdate : " + session);
		super.doUpdate(session);
		if (session != null) {
			this.redisTempate.opsForValue().set(session.getId().toString(),
					session, 1800);
		}
	}
	@Override
	protected void doDelete(Session session) { // session的删除处理
		log.info("*** doDelete : " + session);
		super.doDelete(session);
		this.redisTempate.delete(session.getId().toString());
	}
}