package info.yinhua.core.data.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import info.yinhua.core.data.model.TMenu;

@Repository
public interface TMenuMapper {
	List<TMenu> getList(Collection<String> authorities);
}
