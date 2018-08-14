package info.yinhua.core.web.type;

import java.io.Serializable;

public class DataTablesParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6992637743059701702L;
	private int draw;
	private int start;
	private int length;
	private int orderColumn;
	private String orderDir;

	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(int orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}

}