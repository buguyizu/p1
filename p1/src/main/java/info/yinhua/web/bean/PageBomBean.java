package info.yinhua.web.bean;

import info.yinhua.core.web.type.Paging;

public class PageBomBean extends Paging<BomBean> {

	private String itemCd;
	private String parentItemCd;
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

}