package info.yinhua.web.jetty;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import info.yinhua.web.jetty.EventSource.Emitter;

//https://github.com/jetty-project/jetty-eventsource-servlet/wiki
//https://github.com/alexcheng1982/server-sent-events-sample
public class UserEventSourceServlet extends EventSourceServlet implements HttpRequestHandler  {

    private static final Logger LOG = LogManager.getLogger(UserEventSourceServlet.class);
    
    //暂时得不到值
	@Autowired
    private SessionRegistry sessionRegistry;

	@Override
	protected EventSource newEventSource(HttpServletRequest request, String clientId) {
		return new UserEventSource();
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
