package info.yinhua.core.mapper;

import info.yinhua.web.bean.BomBean;
import info.yinhua.web.bean.PageBomBean;

import java.util.List;
import java.util.Map;


public interface TBom0Mapper {
	List<BomBean> getList(PageBomBean p);
	Integer getSize(PageBomBean p);
}
