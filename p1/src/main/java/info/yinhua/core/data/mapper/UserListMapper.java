package info.yinhua.core.data.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import info.yinhua.web.bean.PageUserListBean;
import info.yinhua.web.bean.UserBean;

@Repository
public interface UserListMapper {

	List<UserBean> getUserList(PageUserListBean p);
}
