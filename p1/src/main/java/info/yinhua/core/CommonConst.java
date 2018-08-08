package info.yinhua.core;

import java.util.Locale;

public interface CommonConst {
	
	//for message bundle
	//local also defined in struts.xml
	public static final Locale LOCALE = Locale.SIMPLIFIED_CHINESE;
	
	//table M_CODE
	enum Code {
		TYPE("-1"),
		DEPARTMENT("01"),
		GENDER("02"),
		ENABLE("03"),
		AUTHORITY("15");
		
		private String code;
		
		private Code(String code) {
			this.code = code;
		}
		
		public String getCode() {
			return code;
		}
	}

	//application-context-security.xml
	public static final String LOGIN_ERROR = "error";
	public static final String ERROR_0 = "0";
	public static final String LOGIN_ERROR_1 = "1";
	public static final String LOGIN_ERROR_2 = "2";
	public static final String LOGIN_ERROR_3 = "3";
	public static final String LOGIN_ERROR_4 = "4";
	public static final String LOGIN_ERROR_5 = "5";
	public static final String LOGIN_ERROR_6 = "6";
	public static final String LOGIN_ERROR_11 = "11";

	//same to
	//application-context-security.xml
	//login.jsp
	//LoginAction.java
	//UsernamePasswordAuthenticationFilter
	//AbstractRememberMeServices
	public static final String KEY_USERNAME = "u";
	public static final String KEY_PASSWORD = "p";
	public static final String KEY_REMEMBER = "r";
	
	//application-context-security.xml
	//login.jsp
	public static final String PARAM_SOURCE = "source";

	public static final String PAGE_LOGIN = "login";
	public static final String PAGE_SIGNUP = "signup";
	public static final String PAGE_ELSE = "else";

	/** 1:用户注册;2:修改用户信息;3:删除用户;99:其它; */
	public static final String LOG_PARAM_USERNAME = "用户名";
	public static final String LOG_PARAM_AUTHORITY = "权限";
	public static final String LOG_PARAM_3 = "3";
	public static final String LOG_PARAM_4 = "4";
	public static final String LOG_PARAM_99 = "99";
	
	enum LogType{

		USER_REGIST("1", "用户注册"),
		USER_EDIT("2", "修改用户信息"),
		USER_DELETE("3", "删除用户"),
		AUTHORITY_ADD("4", "添加权限"),
		AUTHORITY_DELETE("5", "删除权限"),
		OTHER("99", "其它")
		;
		
		private String type;
		private String content;
		private LogType(String type , String content) {
			this.type = type;
			this.content = content;
		}
		public String type() {
			return this.type;
		}
		public String content() {
			return this.content;
		}
	}
}
