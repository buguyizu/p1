package info.yinhua.web.jetty;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;


//https://github.com/jetty-project/jetty-eventsource-servlet/wiki
//https://github.com/alexcheng1982/server-sent-events-sample
public class UserEventSource implements EventSource {

    private volatile UserEventSourceServlet servlet;
    //暂时没用到
    private HttpServletRequest request;
    private Emitter emitter;

    public UserEventSource(UserEventSourceServlet servlet, HttpServletRequest request) {
    	this.servlet = servlet;
    	this.request = request;
    }
    
	@Override
	public void onOpen(Emitter emitter) throws IOException {
        this.emitter = emitter;
		//query();

        emitter.comment("Start sending movement information.");

        //一开始保用user，后来改用sessionId区别。
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Assert.isInstanceOf(UserDetails.class, auth.getPrincipal());
//		UserDetails user = (UserDetails) auth.getPrincipal();
	}

    public void emitData(String data) throws IOException
    {
        emitter.data(data);
    }
    
    //only send data where the same session
    public void emitData(String data, String sessionId) throws IOException
    {
    	try {
	    	if (sessionId.equals(request.getSession().getId())) {
	    		emitter.id("User Login");
	    		emitter.data(data);
	    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

//不用原来的定时发送消息，而是用事件触发。
//	private void query() throws IOException {
//		emitter.comment("Start sending movement information.");
//		emitter.comment(padding());
//		while(true) {
//			emitter.comment("");
//			String id = "test";
//			emitter.id(id);
//			emitter.data(id);
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				break;
//			}
//		}
//	}
//	
//	private String padding() {
//		StringBuilder builder = new StringBuilder();
//		for (int i = 0; i <= 2048; i++) {
//			builder.append(" ");
//		}
//		return builder.toString();
//	}

	@Override
	public void onClose() {
		servlet.getEsList().remove(this);
		this.servlet = null;
		this.request = null;
	}

	@Override
	public void onResume(Emitter emitter, String lastEventId) throws IOException {
	}
}
