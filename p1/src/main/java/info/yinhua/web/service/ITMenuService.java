package info.yinhua.web.service;

import info.yinhua.core.data.model.TMenu;

import java.util.Collection;
import java.util.List;

public interface ITMenuService {
	List<TMenu> getList(Collection<String> authorities);

}
