package info.yinhua.web.action.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import info.yinhua.core.CommonConst;
import info.yinhua.core.context.security.NormalUser;
import info.yinhua.core.context.security.UserManager;
import info.yinhua.core.service.UserListService;
import info.yinhua.web.action.PagingAction;
import info.yinhua.web.bean.PageUserBean;
import info.yinhua.web.bean.UserBean;

@Controller
public class UserListAction extends PagingAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//override ActionSupport
    protected static Logger LOG = LogManager.getLogger(UserAction.class);
	
	private PageUserBean p = new PageUserBean();
	private String error;
	
	@Autowired
	private UserListService userListService;

	@Override
	public String init() throws Exception {
		p.setStatus(Boolean.TRUE.toString());

		return SUCCESS;
	}
	

	public String search() throws Exception {
		
		List<UserBean> userList = userListService.search(p);

		
		return SUCCESS;
	}

	@Override
	public String list() throws Exception {

		
		return SUCCESS;
	}


	public PageUserBean getP() {
		return p;
	}

	public void setP(PageUserBean p) {
		this.p = p;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
