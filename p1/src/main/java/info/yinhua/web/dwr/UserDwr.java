package info.yinhua.web.dwr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import info.yinhua.core.context.security.UserManager;

@Component
public class UserDwr {

	@Autowired
	private UserManager userManager;
	
	public int removeUser(String username) {
		userManager.deleteUser(username);
		
		return 0;
	}
}
