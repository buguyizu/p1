package info.yinhua.web.action.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import info.yinhua.core.CommonConst;
import info.yinhua.web.action.BaseAction;

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
	private String exception;

	@Override
	public String init() {
		return SUCCESS;
	}
	
	public String error() {
		if (StringUtils.isEmpty(error))
			setError(CommonConst.ERROR_0);
		return SUCCESS;
	}
	
	/**
	 * 看定数的注释
	 */
	@Override
    public String execute() throws Exception {
		
//		SPRING_SECURITY_LAST_EXCEPTION.message
//		info.yinhua.core.context.security.FailureHandler
		if (error != null) {

			if (CommonConst.LOGIN_ERROR_1.equals(error)) {
				addActionError(getText(CommonConst.ME_LOGIN_001));

			} else if (CommonConst.LOGIN_ERROR_2.equals(error)) {
				addActionError(getText(CommonConst.ME_LOGIN_001));

			} else if (CommonConst.LOGIN_ERROR_3.equals(error)) {
				addActionError(getText(CommonConst.ME_LOGIN_002));

			} else if (CommonConst.LOGIN_ERROR_4.equals(error)) {
				addActionError(getText(CommonConst.ME_LOGIN_004));

			} else if (CommonConst.LOGIN_ERROR_5.equals(error)) {
				addActionError(getText(CommonConst.MI_SESSION_001));

			} else if (CommonConst.LOGIN_ERROR_11.equals(error)) {
				addActionError(getText(CommonConst.ME_LOGIN_011));
			}
		}
		
		if (CommonConst.PAGE_SIGNUP.equals(source) && CommonConst.ERROR_0.equals(error)) {
			addActionMessage(getText(CommonConst.MI_SIGNUP_001, new String[] { u }));
			return LOGIN;

		} else {
		
			//http://stackoverflow.com/questions/26101738/why-is-the-anonymoususer-authenticated-in-spring-security
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Assert.notNull(auth);
			
			//认证失败时保持用户名
			LOG.debug("name: " + auth.getName());
			if (auth instanceof AnonymousAuthenticationToken && StringUtils.isEmpty(u)) {

				HttpServletRequest request = ServletActionContext.getRequest();
				u = (String) request.getSession().getAttribute(CommonConst.KEY_USERNAME);
				r = (String) request.getSession().getAttribute(CommonConst.KEY_REMEMBER);
				request.getSession().removeAttribute(CommonConst.KEY_USERNAME);
				request.getSession().removeAttribute(CommonConst.KEY_REMEMBER);
				return LOGIN;

			} else {
				return HOME;
			}
		}

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

	public String getException() {
		return exception;
	}

	//本来出错时用<s:property value="exception" />可以在页面上显示出错消息
	//redirectAction后，用这个字段存储出错消息，然后在页面上显示。(error.jsp)
	public void setException(String exception) {
		this.exception = exception;
	}

}
