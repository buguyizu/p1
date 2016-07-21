package info.yinhua.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import info.yinhua.core.CommonConst;
import info.yinhua.core.db.model.MCode;
import info.yinhua.core.service.UserDetailService;
import info.yinhua.web.bean.PageUserBean;
import info.yinhua.web.service.IMCodeService;

@Controller
public class UserAction extends PagingAction {

	private final Logger logger = LogManager.getLogger(getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public String login() {
		
//		SPRING_SECURITY_LAST_EXCEPTION.message
//		error
		HttpServletRequest request = ServletActionContext.getRequest();
		String error = request.getParameter(CommonConst.LOGIN_ERROR);
		if (error != null) {
			if (CommonConst.LOGIN_ERROR_1.equals(error)) {
				
			} else if (CommonConst.LOGIN_ERROR_2.equals(error)) {
				
			} else if (CommonConst.LOGIN_ERROR_3.equals(error)) {
				
			} else if (CommonConst.LOGIN_ERROR_4.equals(error)) {
				
			}
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		assert auth == null;
		logger.debug("name: " + auth.getName());
		if (auth != null && !"anonymousUser".equals(auth.getName())) {
			return "home";
		}

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
