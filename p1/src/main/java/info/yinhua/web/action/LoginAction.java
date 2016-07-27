package info.yinhua.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
	private String source;

	@Override
	public String init() {
		return SUCCESS;
	}
	
	public String error() {
		if (StringUtils.isEmpty(getError()))
			setError(CommonConst.LOGIN_ERROR_0);
		return SUCCESS;
	}
	
	@Override
    public String execute() throws Exception {
		
//		SPRING_SECURITY_LAST_EXCEPTION.message
//		info.yinhua.core.context.security.FailureHandler
		if (getError() != null) {
			if (CommonConst.LOGIN_ERROR_1.equals(getError())) {
				addActionError(getText(CommonConst.ME_LOGIN_001));
			} else if (CommonConst.LOGIN_ERROR_6.equals(getError())) {
				
			}
		}
		
		//http://stackoverflow.com/questions/26101738/why-is-the-anonymoususer-authenticated-in-spring-security
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Assert.notNull(auth);
		
		LOG.debug("name: " + auth.getName());
		if (auth instanceof AnonymousAuthenticationToken) {
			HttpServletRequest request = ServletActionContext.getRequest();
			u = (String) request.getSession().getAttribute(CommonConst.KEY_USERNAME);
			r = (String) request.getSession().getAttribute(CommonConst.KEY_REMEMBER);
			request.getSession().removeAttribute(CommonConst.KEY_USERNAME);
			request.getSession().removeAttribute(CommonConst.KEY_REMEMBER);
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
