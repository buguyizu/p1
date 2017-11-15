package info.yinhua.core.data.model;

public class TMenu extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String menuCode;
	private String menuName;
	private String menuUrl;
	private String menuAuthority;
	private String menuLevel;
	private Byte menuOrder;
	private String menuComment;

	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuAuthority() {
		return menuAuthority;
	}
	public void setMenuAuthority(String menuAuthority) {
		this.menuAuthority = menuAuthority;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public Byte getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(Byte menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuComment() {
		return menuComment;
	}
	public void setMenuComment(String menuComment) {
		this.menuComment = menuComment;
	}
}
