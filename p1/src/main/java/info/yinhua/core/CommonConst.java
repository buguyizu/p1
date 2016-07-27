package info.yinhua.core;

public interface CommonConst {
	enum Code {
		DEPARTMENT("01"),
		AUTHORITY("02");
		
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
	public static final String LOGIN_ERROR_0 = "0";
	public static final String LOGIN_ERROR_1 = "1";
	public static final String LOGIN_ERROR_2 = "2";
	public static final String LOGIN_ERROR_3 = "3";
	public static final String LOGIN_ERROR_4 = "4";
	public static final String LOGIN_ERROR_6 = "6";
	
	public static final String ME_LOGIN_001 = "ME-LOGIN-001";
	public static final String ME_LOGIN_002 = "ME-LOGIN-002";
	public static final String ME_LOGIN_003 = "ME-LOGIN-003";

	public static final String ME_INPUT_001 = "ME-INPUT-001";
	public static final String ME_INPUT_002 = "ME-INPUT-002";
	public static final String ME_INPUT_003 = "ME-INPUT-003";
	public static final String ME_INPUT_004 = "ME-INPUT-004";
	public static final String ME_INPUT_005 = "ME-INPUT-005";
	public static final String ME_INPUT_006 = "ME-INPUT-006";

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
}
