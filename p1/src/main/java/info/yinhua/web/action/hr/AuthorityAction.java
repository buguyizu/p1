package info.yinhua.web.action.hr;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

import info.yinhua.core.context.security.Authority;
import info.yinhua.core.context.security.NormalUser;
import info.yinhua.core.context.security.UserManager;
import info.yinhua.web.action.PagingAction;

@Controller
public class AuthorityAction extends PagingAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//override ActionSupport
    protected static Logger LOG = LogManager.getLogger(AuthorityAction.class);

	@Autowired
	private UserManager userManager;

	private Set<String> authorities;
	private String[] users;

	public String[] getUsers() {
		return users;
	}

	public void setUsers(String[] users) {
		this.users = users;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	@Override
	public String init() throws Exception {

		return SUCCESS;
	}

	public String users() throws Exception {
		
		return SUCCESS;
	}

	public String authorities() throws Exception {

		if (users != null) {
			NormalUser user = (NormalUser) userManager.loadUserByUsername(users[0]);
			Collection<GrantedAuthority> collection = user.getAuthorities();

			if (authorities == null) {
				authorities = new LinkedHashSet<>();
			}
			for (GrantedAuthority a : collection) {
				String role = Authority.getAuthority(a.getAuthority());
				authorities.add(role);
			}
		}
		return SUCCESS;
	}
}
