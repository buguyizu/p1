package info.yinhua.web.bean;

public class BomBean {
	private String itemCd;
	private String parentItemCd;
	private Integer level;
	private String levelCd;
	/**
	 * @return the itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}
	/**
	 * @param itemCd the itemCd to set
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	/**
	 * @return the parentItemCd
	 */
	public String getParentItemCd() {
		return parentItemCd;
	}
	/**
	 * @param parentItemCd the parentItemCd to set
	 */
	public void setParentItemCd(String parentItemCd) {
		this.parentItemCd = parentItemCd;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * @return the levelCd
	 */
	public String getLevelCd() {
		return levelCd;
	}
	/**
	 * @param levelCd the levelCd to set
	 */
	public void setLevelCd(String levelCd) {
		this.levelCd = levelCd;
	}
}