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

	/** 认证失败 */
	public static final String ME_LOGIN_001 = "ME-LOGIN-001";
	public static final String ME_LOGIN_002 = "ME-LOGIN-002";
	public static final String ME_LOGIN_003 = "ME-LOGIN-003";
	public static final String ME_LOGIN_004 = "ME-LOGIN-004";
	public static final String ME_LOGIN_005 = "ME-LOGIN-005";
	/** 重新登录 */
	public static final String ME_LOGIN_011 = "ME-LOGIN-011";
	
	public static final String ME_INPUT_001 = "ME-INPUT-001";
	public static final String ME_INPUT_002 = "ME-INPUT-002";
	public static final String ME_INPUT_003 = "ME-INPUT-003";
	public static final String ME_INPUT_004 = "ME-INPUT-004";
	public static final String ME_INPUT_005 = "ME-INPUT-005";
	public static final String ME_INPUT_006 = "ME-INPUT-006";

	public static final String ME_USER_001 = "ME-USER-001";
	public static final String ME_USER_002 = "ME-USER-002";

	public static final String ME_BACK_001 = "ME-BACK-001";


	/** 用户已存在 */
	public static final String ME_SIGNUP_001 = "ME-SIGNUP-001";

	public static final String MW_USER_002 = "MW-USER-002";
	
	/** 注册成功 */
	public static final String MI_SIGNUP_001 = "MI-SIGNUP-001";
	public static final String MI_USER_001 = "MI-USER-001";
	public static final String MI_USER_002 = "MI-USER-002";
	public static final String MI_USER_003 = "MI-USER-003";

	/** 会话 */
	public static final String MI_SESSION_001 = "MI-SESSION-001";

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
	
	//defalut role (authority)
	public static final String ROLE_USER = "USER";
	public static final String USER_SYSTEM = "system";
	
	public static final String PAGE_LOGIN = "login";
	public static final String PAGE_SIGNUP = "signup";
	public static final String PAGE_ELSE = "else";

	/** 1:用户注册;2:修改用户信息;3:删除用户;99:其它; */
	public static final String LOG_PARAM_USERNAME = "用户名";
	public static final String LOG_PARAM_2 = "2";
	public static final String LOG_PARAM_3 = "3";
	public static final String LOG_PARAM_4 = "4";
	public static final String LOG_PARAM_99 = "99";
	
	enum LogType{

		USER_REGIST("1", "用户注册"),
		USER_EDIT("2", "修改用户信息"),
		USER_DELETE("3", "删除用户"),
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
