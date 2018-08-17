package info.yinhua.web.bean;

import info.yinhua.core.web.type.PaginatedList;

public class PageUserListBean extends PaginatedList<UserBean> {
	private String username;
	private String cd;
	private String name;
	private String department;
	private String gender;
	private String status;
	private String comment;
	private String passwordOrigin;
	private String passwordNew;
	private String passwordNew2;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the cd
	 */
	public String getCd() {
		return cd;
	}
	/**
	 * @param cd the cd to set
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the passwordOrigin
	 */
	public String getPasswordOrigin() {
		return passwordOrigin;
	}
	/**
	 * @param passwordOrigin the passwordOrigin to set
	 */
	public void setPasswordOrigin(String passwordOrigin) {
		this.passwordOrigin = passwordOrigin;
	}
	/**
	 * @return the passwordNew
	 */
	public String getPasswordNew() {
		return passwordNew;
	}
	/**
	 * @param passwordNew the passwordNew to set
	 */
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	/**
	 * @return the passwordNew2
	 */
	public String getPasswordNew2() {
		return passwordNew2;
	}
	/**
	 * @param passwordNew2 the passwordNew2 to set
	 */
	public void setPasswordNew2(String passwordNew2) {
		this.passwordNew2 = passwordNew2;
	}
}