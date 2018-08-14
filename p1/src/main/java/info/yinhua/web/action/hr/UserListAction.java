package info.yinhua.web.action.hr;

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
public class UserListAction extends PagingAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//override ActionSupport
    protected static Logger LOG = LogManager.getLogger(UserListAction.class);
	
	private PageUserListBean p = new PageUserListBean();
	private DataTablesType dt;
	private DataTablesParam dp;
	
	@Autowired
	private UserListService userListService;

	@Override
	public String init() throws Exception {
		p.setStatus(Boolean.TRUE.toString());

		return SUCCESS;
	}

	public void validateSearch() {

		// http://prathap-puppala.blogspot.hk/2011/06/struts-2-pagination-example.html
		// https://www.datatables.net/manual/styling/bootstrap-simple.html
		String[] sortCriterion = new String[] { "username", "code", "name", "gender", "department" };
		if (dp.getOrderColumn() >= 0 && dp.getOrderColumn() < sortCriterion.length)
			p.setSortCriterion(sortCriterion[dp.getOrderColumn()]);
		else
			p.setSortCriterion(sortCriterion[0]);

		p.setSortType(dp.getOrderDir());

		if (PaginatedList.SIZE_LIST.contains(dp.getLength()))
			p.setObjectsPerPage(dp.getLength());
		else
			p.setObjectsPerPage(PaginatedList.SIZE_LIST.get(0));

		p.setOffset(dp.getStart());
	}

	public String search() throws Exception {

		userListService.search(p);
		dt = new DataTablesType();
		dt.setDraw(dp.getDraw());

		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		for (UserBean bean : p.getList()) {
			Map<String, String> row = new HashMap<String, String>();
			//TODO
			//row.put(DataTablesType.ROWID, "R_" + bean.getUsername());
			row.put("0", bean.getUsername());
			row.put("1", bean.getCode());
			row.put("2", bean.getName());
			row.put("3", bean.getGender());
			row.put("4", bean.getDepartment());
			row.put("5", bean.getComment());
			row.put("6", bean.getVersion());
			rows.add(row);
		}
		dt.setData(rows);
		dt.setRecordsTotal(p.getFullListSize());
		dt.setRecordsFiltered(dt.getRecordsTotal());

		return SUCCESS;
	}

	@Override
	public String list() throws Exception {

		
		return SUCCESS;
	}

	public PageUserListBean getP() {
		return p;
	}

	public void setP(PageUserListBean p) {
		this.p = p;
	}

	public DataTablesType getDt() {
		return dt;
	}

	public void setDt(DataTablesType dt) {
		this.dt = dt;
	}


	public DataTablesParam getDp() {
		return dp;
	}


	public void setDp(DataTablesParam dp) {
		this.dp = dp;
	}
}
