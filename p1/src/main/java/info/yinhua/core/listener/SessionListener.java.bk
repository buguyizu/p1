package info.yinhua.core.listener;

import info.yinhua.core.db.model.MSession;
import info.yinhua.core.mapper.MSessionMapper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
		trace(event, (byte) 1);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.session.HttpSessionEventPublisher#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		super.sessionDestroyed(event);
		trace(event, (byte) 2);
	}
	
	//存在就添加，否则更新
	@Transactional
	private void trace(HttpSessionEvent event, byte type) {

		ServletContext servletContext = event.getSession().getServletContext();
		
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);

		MSessionMapper mSessionMapper = (MSessionMapper) applicationContext
				.getBean("MSessionMapper");

		MSession s = new MSession();
		s.setSessionId(event.getSession().getId());
		
		if (type == 1) {
			mSessionMapper.delete(s.getSessionId());

			//TODO 登录后取不到用户信息
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				
				if (auth.getPrincipal() instanceof String) {
					s.setUser((String) auth.getPrincipal());
				} else if (auth.getPrincipal() instanceof User) {
					s.setUser(((User) auth.getDetails()).getUsername());
				}
				WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
				if (details != null)
					s.setIp(details.getRemoteAddress());
			}
			
			mSessionMapper.insert(s);
		} else {
			mSessionMapper.update(s);
		}
	}
}
