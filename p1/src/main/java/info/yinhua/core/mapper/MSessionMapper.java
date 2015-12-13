package info.yinhua.core.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import info.yinhua.core.db.model.MCode;
import info.yinhua.core.db.model.MSession;

@Repository
public interface MSessionMapper {

	void insert(MSession mSession);
	void update(MSession mSession);
}
