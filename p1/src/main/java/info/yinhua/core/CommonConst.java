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

	//same to
	//application-context-security.xml
	//login.jsp
	//LoginAction.java
	//UsernamePasswordAuthenticationFilter
	//AbstractRememberMeServices
	public static final String KEY_USERNAME = "u";
	public static final String KEY_PASSWORD = "p";
	public static final String KEY_REMEMBER = "r";
	
	
	
}
