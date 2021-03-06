package info.yinhua.core.web.type;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Result of DataTables
 * @author user
 *
 */
public class DataTablesType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2398365881621370061L;

	public static final String ROWID = "DT_RowId";
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<Map<String, String>> data;

	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}
	/**
	 * @return the recordsTotal
	 */
	public int getRecordsTotal() {
		return recordsTotal;
	}
	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	/**
	 * @return the recordsFiltered
	 */
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	/**
	 * @return the data
	 */
	public List<Map<String, String>> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}
}