package info.yinhua.core.listener;

import info.yinhua.core.db.model.MSession;
import info.yinhua.core.mapper.MSessionMapper;

import javax.servlet.http.HttpSessionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionListener extends HttpSessionEventPublisher {

	private final Logger logger = LogManager.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.session.HttpSessionEventPublisher#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		super.sessionCreated(event);

	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.session.HttpSessionEventPublisher#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		super.sessionDestroyed(event);
	}
	
	//存在就添加，否则更新
	private void trace(HttpSessionEvent event) {

		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getSession()
						.getServletContext());

		MSessionMapper mSessionMapper = (MSessionMapper) applicationContext
				.getBean("MSessionMapper");

		MSession s = new MSession();
		s.setSessionId(event.getSession().getId());
		mSessionMapper.insert(s);
	}
}
