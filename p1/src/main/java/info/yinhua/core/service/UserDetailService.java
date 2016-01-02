package info.yinhua.core.service;

import info.yinhua.core.db.model.UserDetail;
import info.yinhua.core.mapper.UserDetailMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

	@Autowired
	private UserDetailMapper userDetailMapper;
	
	public UserDetail get(String username) {
		return userDetailMapper.select(username);
	}

	public void insert(UserDetail userDetail) {
		userDetailMapper.insert(userDetail);
	}

	public void update(UserDetail userDetail) {
		userDetailMapper.update(userDetail);
	}
	
	public void delete(String username) {
		
		userDetailMapper.delete(username);
	}
}
