package info.yinhua.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.yinhua.core.data.mapper.UserListMapper;
import info.yinhua.web.bean.PageUserBean;
import info.yinhua.web.bean.UserBean;

@Service
public class UserListService {

	@Autowired
	private UserListMapper userlistMapper;
	
//	@Cacheable(value = "USER_DATA", key = "#username")
//	public NormalUser get(String username) {
//		return userMapper.exists(username);
//	}

	public List<UserBean> search(PageUserBean p) {
		return userlistMapper.getUserList(p);
	}

}
