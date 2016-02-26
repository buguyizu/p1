package info.yinhua.web.service;

import info.yinhua.core.db.model.MCode;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

public interface IMCodeService {
	MCode getCode(String type, String cd);

	@Cacheable(value = "CODE_DATA", key = "#type")
	List<MCode> getList(String type);
}
