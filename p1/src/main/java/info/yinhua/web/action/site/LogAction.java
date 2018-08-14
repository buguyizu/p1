package info.yinhua.web.action.site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import info.yinhua.core.service.UserListService;
import info.yinhua.core.web.type.DataTablesParam;
import info.yinhua.core.web.type.DataTablesType;
import info.yinhua.core.web.type.PaginatedList;
import info.yinhua.web.action.PagingAction;
import info.yinhua.web.bean.PageUserListBean;
import info.yinhua.web.bean.UserBean;

@Controller
public class LogAction extends PagingAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//override ActionSupport
    protected static Logger LOG = LogManager.getLogger(LogAction.class);
	

	@Override
	public String init() throws Exception {

		return SUCCESS;
	}

}
