package info.yinhua.web.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import info.yinhua.core.mapper.TBom0Mapper;
import info.yinhua.core.db.model.MCode;
import info.yinhua.web.bean.BomBean;
import info.yinhua.web.bean.PageBomBean;
import info.yinhua.web.service.IMCodeService;
import info.yinhua.web.service.ITBomService;

@Service
public class TBomService implements ITBomService {

	@Autowired
	private TBom0Mapper tBom0Mapper;

	@Override
	public void search(PageBomBean p) {
		int size = tBom0Mapper.getSize(p);
		p.setFullListSize(size);
		
		if (size > 0) {
			List<BomBean> list = tBom0Mapper.getList(p);
			p.setList(list);
		}
	}

}
