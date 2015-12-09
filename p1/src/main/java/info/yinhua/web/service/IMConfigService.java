package info.yinhua.web.service;

import java.io.InputStream;
import java.util.Properties;

public interface IMConfigService {
	/**
	 * 取授权文件
	 * @return
	 */
	Properties getLicense();
	/**
	 * 取菜单文件
	 * @return
	 */
	InputStream getMenu();
	/**
	 * 1:授权文件;3:菜单文件;
	 * @param i
	 */
	void save(Integer i);
}
