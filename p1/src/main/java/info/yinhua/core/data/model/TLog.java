package info.yinhua.core.data.model;

import java.sql.Timestamp;

public class TLog extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8525680068114691708L;
	private int id;
	private String logType;
	private String logContent;
	private String logParam;
	private String createUser;
	private Timestamp createDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getLogParam() {
		return logParam;
	}
	public void setLogParam(String logParam) {
		this.logParam = logParam;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
}
