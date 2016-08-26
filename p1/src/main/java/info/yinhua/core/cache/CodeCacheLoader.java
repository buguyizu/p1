package info.yinhua.core.cache;

import info.yinhua.core.data.model.MCode;
import info.yinhua.web.service.IMCodeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;

@Component("codeCacheLoader")
public class CodeCacheLoader extends CacheLoader<String, List<MCode>> {

	@Autowired
	protected IMCodeService mCodeService;
	
	@Override
	public List<MCode> load(String key) throws Exception {
		// TODO Auto-generated method stub
		return mCodeService.getList(key);
	}

}
