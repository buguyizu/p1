package info.yinhua.core.data.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import info.yinhua.core.context.security.NormalUser;

@Repository
public interface NormalUserMapper {

	List<String> exists(String username);
	List<Map<String, Object>> getUsers(String username);
	List<Map<String, Object>> getRoles(String username);
	void create(NormalUser normalUser);
	void insertRoles(String username, String role);
	void update(NormalUser normalUser);
	void changePassword(String username, String password);
	void delete(String username);
	void deleteRoles(String username);
}
