package info.yinhua.core.data.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import info.yinhua.core.data.model.TLog;

@Repository
public interface TLogMapper {

	List<TLog> get(TLog log);
	void log(TLog log);
}
