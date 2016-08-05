package info.yinhua.web.action;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import info.yinhua.core.CommonConst;
import info.yinhua.core.context.security.NormalUser;
import info.yinhua.core.context.security.UserManager;
import info.yinhua.web.bean.PageUserBean;
import info.yinhua.web.bean.UserBean;

@Controller
public class UserAction extends PagingAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//override ActionSupport
    protected static Logger LOG = LogManager.getLogger(UserAction.class);
	
	private PageUserBean p = new PageUserBean();
	private UserBean user = new UserBean();
	private String error;
	
	@Autowired
	private UserManager userManager;

	@Override
	public String init() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			return LOGIN;
		}
		NormalUser normalUser = (NormalUser) auth.getPrincipal();
		user.setCode(normalUser.getCode());
		user.setName(normalUser.getName());
		user.setGender(normalUser.getGender());
		user.setDepartment(normalUser.getDepartment());
		user.setComment(normalUser.getComment());
		
		p.setCd(user.getCode());
		p.setName(user.getName());
		p.setGender(user.getGender());
		p.setDepartment(user.getDepartment());
		p.setComment(user.getComment());
		
		return SUCCESS;
	}
	
    public void validateJoin() {
    	//https://struts.apache.org/docs/form-validation.html
    	
    	if (CommonConst.PAGE_SIGNUP.equals(getSource())) {
    		
	    	if ( !StringUtils.hasText(user.getUsername()) ) { 
	            addFieldError( "user.username", getText(CommonConst.ME_INPUT_001, 
	            		new String[] { getText("username") } ) );
	        }
	    	if ( !StringUtils.hasText(user.getPassword())
	    			|| !StringUtils.hasText(user.getPassword2())
	    			|| !user.getPassword().equals(user.getPassword2())) { 
	            addFieldError( "user.password", getText(CommonConst.ME_INPUT_004, 
	            		new String[] { getText("password") } ) );
	        }
    	}
    }
    
    
	public String join() throws Exception {

    	if (CommonConst.PAGE_SIGNUP.equals(getSource())) {
    		try {
    			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    			authorities.add(new SimpleGrantedAuthority(CommonConst.ROLE_USER));
    			user.setAuthorities(authorities);
    			userManager.createUser(user);
    		} catch (Exception e) {
//    			e.printStackTrace();
    			throw e;
    		}
    		
    		return LOGIN;
    	} else {
    		return SUCCESS;
    	}
	}
	

	public String update() throws Exception {
		
		
		return SUCCESS;
		
	}
	public String changePassword() throws Exception {
		return SUCCESS;
		
	}

	public UserBean getUser() {
		return user;
	}

	public PageUserBean getP() {
		return p;
	}

	public void setP(PageUserBean p) {
		this.p = p;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
