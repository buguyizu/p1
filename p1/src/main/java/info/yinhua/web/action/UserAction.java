package info.yinhua.web.action;

import java.util.HashSet;
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
	public String init() throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, auth);
		Assert.isInstanceOf(NormalUser.class, auth.getPrincipal());
		
		NormalUser normalUser = (NormalUser) auth.getPrincipal();
		NormalUser dbUser = (NormalUser) userManager.loadUserByUsername(normalUser.getUsername());
		
		//重新登录
		//http://forum.spring.io/forum/spring-projects/security/35809-how-to-let-admin-to-force-user-to-logout
		if (!normalUser.getVersion().equals(dbUser.getVersion())) {
			SecurityContextHolder.getContext().setAuthentication(null);
			setSource("user-info");
			setError(CommonConst.LOGIN_ERROR_11);
			user.setUsername(normalUser.getUsername());
			return LOGIN;
		}
		
		getUserInfo();
		p.setCd(user.getCode());
		p.setName(user.getName());
		p.setGender(user.getGender());
		p.setStatus(Boolean.toString(user.isEnabled()));
		p.setDepartment(user.getDepartment());
		p.setComment(user.getComment());

		if (StringUtils.isEmpty(source)) {
			setSource("1");
		} else if ("4".equals(source)) {
			setSource("1");
			addActionMessage(getText(CommonConst.MI_USER_001, new String[] { user.getUsername() }));
		} else if ("5".equals(source)) {
			setSource("1");
			addActionMessage(getText(CommonConst.MI_USER_002, new String[] { user.getUsername() }));
		}
		
		return SUCCESS;
	}
	
	private void getUserInfo() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, auth);
		Assert.isInstanceOf(NormalUser.class, auth.getPrincipal());
		
		NormalUser normalUser = (NormalUser) auth.getPrincipal();
		user.setUsername(normalUser.getUsername());
		user.setCode(normalUser.getCode());
		user.setName(normalUser.getName());
		user.setGender(normalUser.getGender());
		user.setDepartment(normalUser.getDepartment());
		user.setComment(normalUser.getComment());
		user.setAuthorities((Set<GrantedAuthority>) normalUser.getAuthorities());
		user.setEnabled(normalUser.isEnabled());
	}
	
	
    public void validateJoin() {
    	//https://struts.apache.org/docs/form-validation.html
    	
    	if (CommonConst.PAGE_SIGNUP.equals(source)) {
    		
	    	if ( !StringUtils.hasText(user.getUsername()) ) { 
	            addFieldError( "user.username", getText(CommonConst.ME_INPUT_001,
	            		new String[] { getText("username") } ) );
	        }
	    	if ( !StringUtils.hasText(user.getPassword())) {
	            addFieldError( "user.password", getText(CommonConst.ME_INPUT_001, 
	            		new String[] { getText("password") } ) );
	    	} else if (!StringUtils.hasText(user.getPassword2())
	    			|| !user.getPassword().equals(user.getPassword2())) { 
	            addFieldError( "user.password", getText(CommonConst.ME_INPUT_004, 
	            		new String[] { getText("password") } ) );
	        }
    	}
    }
    
    
	public String join() throws Exception {

    	if (CommonConst.PAGE_SIGNUP.equals(source)) {
    		try {
    			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    			authorities.add(new SimpleGrantedAuthority(CommonConst.ROLE_USER));
    			user.setAuthorities(authorities);
    			user.setEnabled(true);
    			userManager.createUser(user);
    		} catch (Exception e) {
//    			e.printStackTrace();
    			throw e;
    		}
    		
    		setError("0");
    		return LOGIN;
    	} else {
    		return SUCCESS;
    	}
	}
	

    public void validateUpdate() {
    	setSource("2");
    }
    
	public String update() throws Exception {

		getUserInfo();
		user.setCode(p.getCd());
		user.setName(p.getName());
		user.setGender(p.getGender());
		user.setEnabled(Boolean.parseBoolean(p.getStatus()));
		user.setDepartment(p.getDepartment());
		user.setComment(p.getComment());
		
		user.setPassword("");
		userManager.updateUser(user);

		return SUCCESS;
		
	}
	
    public void validateChangePassword() {

    	setSource("3");
    	if ( !StringUtils.hasText(p.getPasswordOrigin()) ) { 
            addFieldError( "p.passwordOrigin", getText(CommonConst.ME_INPUT_001, 
            		new String[] { getText("user.password.origin") } ) );
        }
    	if ( !StringUtils.hasText(p.getPasswordNew()) ) { 
            addFieldError( "p.passwordNew", getText(CommonConst.ME_INPUT_001, 
            		new String[] { getText("user.password.new") } ) );
        } else if ( !StringUtils.hasText(p.getPasswordNew2())
    			|| !p.getPasswordNew().equals(p.getPasswordNew2())) { 
            addFieldError( "p.passwordNew", getText(CommonConst.ME_INPUT_004, 
            		new String[] { getText("password") } ) );
        }
    }
    
	public String changePassword() throws Exception {
		getUserInfo();
		userManager.changePassword(p.getPasswordOrigin(), p.getPasswordNew());

		
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
