package info.yinhua.web.action;

import info.yinhua.core.db.model.MCode;
import info.yinhua.web.service.ICommonService;
import info.yinhua.web.service.IMCodeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;

import com.opensymphony.xwork2.ActionSupport;

@EnableCaching()
public abstract class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected ICommonService commonService;
	@Autowired
	protected IMCodeService mCodeService;
	
	public List<MCode> getCodeList(String type) {
		return mCodeService.getList(type);
	}
	
	//初期化
	public abstract String init();
	
}
