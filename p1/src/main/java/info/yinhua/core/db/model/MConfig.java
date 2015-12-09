package info.yinhua.core.db.model;

import java.util.Date;

public class MConfig {

	private int id;
	private byte[] blob;
	private Date datetime;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the blob
	 */
	public byte[] getBlob() {
		return blob;
	}
	/**
	 * @param blob the blob to set
	 */
	public void setBlob(byte[] blob) {
		this.blob = blob;
	}
	/**
	 * @return the datetime
	 */
	public Date getDatetime() {
		return datetime;
	}
	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}
