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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import info.yinhua.core.CommonConst;
import info.yinhua.core.data.model.MCode;
import info.yinhua.core.service.UserDetailService;
import info.yinhua.web.bean.PageUserBean;
import info.yinhua.web.service.IMCodeService;

@Controller
public class UserAction extends PagingAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//override ActionSupport
    protected static Logger LOG = LogManager.getLogger(UserAction.class);

	private String error;
	
	private PageUserBean p = new PageUserBean();
	
	@Autowired
	private JdbcUserDetailsManager userManager;

	@Autowired
	private UserDetailService userDetailService;

	@Override
	public String init() {
		
//		applicationContext
		

		return SUCCESS;
	}
	

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

}
