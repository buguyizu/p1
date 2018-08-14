package info.yinhua.core.web.type;

import java.util.Arrays;
import java.util.List;

/**
 * 2016/08/18
 * @see org.displaytag.pagination.PaginatedList
 * @param <T>
 */
public class PaginatedList<T> {

	/**
	 * Front side lengthMenu is in userlist.js
	 * TODO if set 2 in the list, front page can not set default value of 2 in first draw
	 */
	public static final List<Integer> SIZE_LIST = Arrays.asList(10, 25, 50);

	/**
	 * 每页的列表
	 */
	private List<T> list;

	/**
	 * sql获取数据偏移量
	 */
	private int offset;

	/**
	 * 每页记录数 page size
	 */
	private int objectsPerPage;
	/**
	 * 总记录数
	 */
	private int fullListSize;

    private String sortType;
    private String sortCriterion;
	private String searchId;
	
	public int getFullListSize() {
		return fullListSize;
	}

	public List<T> getList() {
		return list;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	/**
	 * 当前页码
	 */
	public int getPageNumber() {
		return ((offset + 1) / objectsPerPage) + 1;
	}

	public String getSearchId() {
		return searchId;
	}

	public String getSortType() {
		return sortType;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @param objectsPerPage the objectsPerPage to set
	 */
	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	/**
	 * @param fullListSize the fullListSize to set
	 */
	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}

	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * @param sortCriterion the sortCriterion to set
	 */
	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	/**
	 * @param searchId the searchId to set
	 */
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
