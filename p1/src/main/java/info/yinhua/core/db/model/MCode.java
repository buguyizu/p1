package info.yinhua.core.db.model;

public class MCode extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fType;
	private String fCode;
	private String fValue;
	/**
	 * @return the fType
	 */
	public String getfType() {
		return fType;
	}
	/**
	 * @param fType the fType to set
	 */
	public void setfType(String fType) {
		this.fType = fType;
	}
	/**
	 * @return the fCode
	 */
	public String getfCode() {
		return fCode;
	}
	/**
	 * @param fCode the fCode to set
	 */
	public void setfCode(String fCode) {
		this.fCode = fCode;
	}
	/**
	 * @return the fValue
	 */
	public String getfValue() {
		return fValue;
	}
	/**
	 * @param fValue the fValue to set
	 */
	public void setfValue(String fValue) {
		this.fValue = fValue;
	}
}
