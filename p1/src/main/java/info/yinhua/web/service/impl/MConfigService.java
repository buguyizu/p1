package info.yinhua.web.service.impl;

import info.yinhua.core.data.mapper.IMConfigDao;
import info.yinhua.core.data.model.MConfig;
import info.yinhua.web.service.IMConfigService;
import info.yinhua.core.util.ZipUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class MConfigService implements IMConfigService {
	
	@Autowired
	private IMConfigDao mConfigDao;
	private final static String MENU_NAME = "menu_zh_CN.xml";

	
	public Properties getLicense() {
		MConfig m = mConfigDao.select(1);
		InputStream in = null;
		try {
			Properties p = ZipUtil.unPack(m.getBlob());
			return p;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @see info.yinhua.core.service.IMConfigService#getMenu()
	 */
	public InputStream getMenu() {
		MConfig m = mConfigDao.select(3);
		if (m == null) {
			return null;
		} else {
			return new ByteArrayInputStream(m.getBlob());
		}
	}
	
	/**
	 * @see info.yinhua.core.service.IMConfigService#save(java.lang.Integer)
	 */
	public void save(Integer i) {
	}
}
