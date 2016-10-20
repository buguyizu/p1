package info.yinhua.web.jetty;

import java.io.IOException;


//https://github.com/jetty-project/jetty-eventsource-servlet/wiki
//https://github.com/alexcheng1982/server-sent-events-sample
public class UserEventSource implements EventSource {
	
	@Override
	public void onOpen(Emitter emitter) throws IOException {
		query(emitter);
	}

	private void query(Emitter emitter) throws IOException {
		emitter.comment("Start sending movement information.");
		emitter.comment(padding());
		while(true) {
			emitter.comment("");
			String id = "test";
			emitter.id(id);
			emitter.data(id);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				break;
			}
		}
		emitter.close();
	}

	@Override
	public void onClose() {
		
	}
	
	@Override
	public void onResume(Emitter emitter, String lastEventId) throws IOException {
		query(emitter);
	}
	
	private String padding() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= 2048; i++) {
			builder.append(" ");
		}
		return builder.toString();
	}
}
