package info.yinhua.core.data.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import info.yinhua.core.data.model.MCode;

@Repository
public interface MCodeMapper {
	List<MCode> getList(Map<String, String> map);
}
