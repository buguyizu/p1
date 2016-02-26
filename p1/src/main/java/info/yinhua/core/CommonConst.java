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
}
