package info.yinhua.core.mapper;

import org.springframework.stereotype.Repository;
import info.yinhua.core.db.model.MSession;

@Repository
public interface MSessionMapper {

	int count(String sessionId);
	void insert(MSession mSession);
	void update(MSession mSession);
	void delete(String sessionId);
}
