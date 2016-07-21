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
	
	public static final String LOGIN_ERROR = "error";
	public static final String LOGIN_ERROR_0 = "0";
	public static final String LOGIN_ERROR_1 = "1";
	public static final String LOGIN_ERROR_2 = "2";
	public static final String LOGIN_ERROR_3 = "3";
	public static final String LOGIN_ERROR_4 = "4";
	public static final String LOGIN_ERROR_6 = "6";
}
