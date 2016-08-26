package info.yinhua.core.web.type;

import java.util.List;

/**
 * 2016/08/18
 * @see org.displaytag.pagination.PaginatedList
 * @param <T>
 */
public class PaginatedList<T> {

	/**
	 * 每页的列表
	 */
	private List<T> list;
	
	/**
	 * 当前页码
	 */
	private int pageNumber = 1;
	
	/**
	 * 每页记录数 page size
	 */
	private int objectsPerPage = 15;
	/**
	 * 总记录数
	 */
	private int fullListSize = 0;

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

	public int getPageNumber() {
		return pageNumber;
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
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
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

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return (pageNumber - 1) * objectsPerPage;
	}
}
