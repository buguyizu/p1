package info.yinhua.core.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.yinhua.core.data.mapper.UserListMapper;
import info.yinhua.web.bean.PageUserListBean;
import info.yinhua.web.bean.UserBean;

@Service
public class UserListService {

	@Autowired
	private UserListMapper userlistMapper;
	
//	@Cacheable(value = "USER_DATA", key = "#username")
//	public NormalUser get(String username) {
//		return userMapper.exists(username);
//	}

	public List<UserBean> search(PageUserListBean p) {
		p.setList(userlistMapper.getUserList(p));
		return p.getList();
	}

	public Collection<String> getUsers(String username) {

		Collection<String> users = userlistMapper.getUsernames(username);

		return users;
	}
}
