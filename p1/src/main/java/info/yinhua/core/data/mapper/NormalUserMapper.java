package info.yinhua.core.data.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import info.yinhua.core.context.security.NormalUser;

@Repository
public interface NormalUserMapper {

	NormalUser exists(String username);
	void create(NormalUser normalUser);
	void insertRoles(String username, String role);
	void update(NormalUser normalUser);
	void delete(String username);
}
