package info.yinhua.core.data.mapper;

import org.springframework.stereotype.Repository;
import info.yinhua.core.data.model.MSession;
import info.yinhua.core.data.model.UserDetail;

@Repository
public interface UserDetailMapper {

	UserDetail select(String username);
	void insert(UserDetail mSession);
	void update(UserDetail mSession);
	void delete(String username);
}
