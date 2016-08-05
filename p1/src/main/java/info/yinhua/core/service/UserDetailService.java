package info.yinhua.core.service;

import info.yinhua.core.data.model.UserDetail;
import info.yinhua.core.context.security.NormalUser;
import info.yinhua.core.data.mapper.NormalUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

	@Autowired
	private NormalUserMapper userMapper;
	
//	@Cacheable(value = "USER_DATA", key = "#username")
//	public NormalUser get(String username) {
//		return userMapper.exists(username);
//	}

	public void insert(NormalUser normalUser) {
		userMapper.create(normalUser);
	}

	public void update(NormalUser normalUser) {
		userMapper.update(normalUser);
	}
	
	public void delete(String username) {
		
		userMapper.delete(username);
	}
}
