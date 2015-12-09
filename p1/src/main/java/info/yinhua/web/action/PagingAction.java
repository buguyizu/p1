package info.yinhua.web.action;


public abstract class PagingAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tableId = "table";
	
	//翻页排序
	public String list() {
		return SUCCESS;
	}

	/**
	 * @return the tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
}
