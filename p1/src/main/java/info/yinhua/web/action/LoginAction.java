package info.yinhua.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import info.yinhua.core.CommonConst;

@Controller
public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String u;
	private String p;
	private String r;
	private String error;

	@Override
	public String init() {
		return SUCCESS;
	}
	
	@Override
    public String execute() throws Exception {
		
//		SPRING_SECURITY_LAST_EXCEPTION.message
//		error
		HttpServletRequest request = ServletActionContext.getRequest();
		String error = request.getParameter(CommonConst.LOGIN_ERROR);
		if (error != null) {
			if (CommonConst.LOGIN_ERROR_1.equals(error)) {
				addActionError(getText(CommonConst.ME_LOGIN_001));
			} else if (CommonConst.LOGIN_ERROR_6.equals(error)) {
				
			}
		}
		
		//http://stackoverflow.com/questions/26101738/why-is-the-anonymoususer-authenticated-in-spring-security
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Assert.notNull(auth);
		
		LOG.debug("name: " + auth.getName());
		if (auth instanceof AnonymousAuthenticationToken) {
			u = (String) request.getSession().getAttribute(CommonConst.KEY_USERNAME);
			r = (String) request.getSession().getAttribute(CommonConst.KEY_REMEMBER);
			return LOGIN;
		}

		return HOME;
	}
	
	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
