package info.yinhua.core.web.type;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

public class Paging<T> implements PaginatedList {

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

    private String sortCriterion;
    private SortOrderEnum sortDirection;
	private String searchId;
	
	@Override
	public int getFullListSize() {
		return fullListSize;
	}

	@Override
	public List<T> getList() {
		return list;
	}

	@Override
	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public String getSearchId() {
		return searchId;
	}

	@Override
	public String getSortCriterion() {
		return sortCriterion;
	}

	@Override
	public SortOrderEnum getSortDirection() {
		return sortDirection;
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
	 * @param sortCriterion the sortCriterion to set
	 */
	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	/**
	 * @param sortDirection the sortDirection to set
	 */
	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
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
	
	public String getSortType() {
		return SortOrderEnum.DESCENDING == sortDirection ? "desc" : null;
	}
}
