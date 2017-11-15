package info.yinhua.web.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.yinhua.core.data.mapper.TMenuMapper;
import info.yinhua.core.data.model.TMenu;
import info.yinhua.web.service.ITMenuService;

@Service
public class TMenuService implements ITMenuService {

	@Autowired
	private TMenuMapper tMenuMapper;
	
	
	public List<TMenu> getList(Collection<String> authorities) {
		return tMenuMapper.getList(authorities);
	}
}
