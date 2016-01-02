package info.yinhua.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import info.yinhua.core.db.model.MCode;
import info.yinhua.core.service.UserDetailService;
import info.yinhua.web.service.IMCodeService;

@Controller
public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String error;
	
	@Autowired
	private JdbcUserDetailsManager userManager;

	@Autowired
	private UserDetailService userDetailService;

	@Override
	public String init() {
//		ActionContext.getContext().getSession().
		
		

		return SUCCESS;
	}
	
	public String login() {
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
