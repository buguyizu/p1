package info.yinhua.core.listener;

import java.util.Date;

import info.yinhua.core.db.model.MSession;
import info.yinhua.core.mapper.MSessionMapper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionListener implements ApplicationListener<ApplicationEvent> {

	private final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private MSessionMapper mSessionMapper;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof HttpSessionCreatedEvent) {
			HttpSessionCreatedEvent e = (HttpSessionCreatedEvent) event;
			trace(e.getSession(), (byte) 1);
		} else if (event instanceof HttpSessionDestroyedEvent) {
			HttpSessionDestroyedEvent e = (HttpSessionDestroyedEvent) event;
			trace(e.getSession(), (byte) 2);
		}
//		else if (event instanceof AuthenticationSuccessEvent) {
//			AuthenticationSuccessEvent e = (AuthenticationSuccessEvent) event;
//			WebAuthenticationDetails details =
//					(WebAuthenticationDetails) e.getAuthentication().getDetails();
//
//			MSession s = new MSession();
//			s.setSessionId(details.getSessionId());
//			s.setUser(e.getAuthentication().getName());
//			
//			mSessionMapper.update(s);
//		}
		else if (event instanceof SessionFixationProtectionEvent) {
			login((SessionFixationProtectionEvent) event);
		}
		
		else if (event instanceof ContextStartedEvent) {
			
		} else if (event instanceof ContextClosedEvent) {
			
		}
	}
	
	/**
	 * insert or update data of session
	 * @param session
	 * @param type
	 */
	@Transactional
	private void trace(HttpSession session, byte type) {

		MSession s = new MSession();
		s.setSessionId(session.getId());
		
		if (type == 1) {
			mSessionMapper.delete(s.getSessionId());

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				
				WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
				if (details != null)
					s.setIp(details.getRemoteAddress());

				//这里只能取得匿名认证
				if (auth.getPrincipal() instanceof String)
					s.setUser((String) auth.getPrincipal());
			}
			
			mSessionMapper.insert(s);
		} else if (type == 2) {
			s.setDateEnd(new Date());
			mSessionMapper.update(s);
		}
	}
	
	//after login, a new session started
	private void login(SessionFixationProtectionEvent event) {

		WebAuthenticationDetails details =
				(WebAuthenticationDetails) event.getAuthentication().getDetails();
		User user =
				(User) event.getAuthentication().getPrincipal();
		
		MSession s = new MSession();
		s.setSessionId(event.getNewSessionId());
		s.setIp(details.getRemoteAddress());
		s.setUser(user.getUsername());
		
		mSessionMapper.update(s);
		logger.trace("auth updated.");
	}
}
