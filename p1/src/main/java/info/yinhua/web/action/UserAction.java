package info.yinhua.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import info.yinhua.core.context.security.UserManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.ValidationUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import info.yinhua.core.CommonConst;
import info.yinhua.core.data.model.MCode;
import info.yinhua.core.service.UserDetailService;
import info.yinhua.web.bean.PageUserBean;
import info.yinhua.web.bean.UserBean;
import info.yinhua.web.service.IMCodeService;

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

	@Autowired
	private UserDetailService userDetailService;

	@Override
	public String init() {
		
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
	

	public UserBean getUser() {
		return user;
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
