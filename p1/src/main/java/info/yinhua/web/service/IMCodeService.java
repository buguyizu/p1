package info.yinhua.web.service;

import info.yinhua.core.db.model.MCode;

import java.util.List;

public interface IMCodeService {
	MCode getCode(String type, String cd);
	List<MCode> getList(String type);
}
