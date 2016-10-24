package info.yinhua.web.jetty;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

//https://github.com/jetty-project/jetty-eventsource-servlet/wiki
//https://github.com/alexcheng1982/server-sent-events-sample
//https://github.com/marschall/event-source-sample
@WebServlet(asyncSupported = true, value = "/sse/*")
public class UserEventSourceServlet extends EventSourceServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected EventSource newEventSource(HttpServletRequest request, String clientId) {
		//https://mythinkpond.com/2010/03/22/spring-application-context/
		//http://forum.spring.io/forum/spring-projects/container/38349-how-to-access-spring-application-context-from-another-servlet-not-in-spring-context
		//因为servlet不是spring创建的，所以只有通过这种方式(WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()))得到sessionRegistry，通过注入不行。
//		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
//		SessionRegistry sessionRegistry = (SessionRegistry) context.getBean("sessionRegistry");
		
		UserEventSource eventSource = new UserEventSource(this, request);
		
		return eventSource;
	}
}
