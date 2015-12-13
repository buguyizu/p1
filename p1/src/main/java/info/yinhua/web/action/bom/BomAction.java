package info.yinhua.web.action.bom;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.displaytag.properties.SortOrderEnum;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import info.yinhua.web.service.ITBomService;
import info.yinhua.web.action.PagingAction;
import info.yinhua.web.bean.PageBomBean;

@Controller
public class BomAction extends PagingAction {

	private final Logger logger = LogManager.getLogger(getClass());
	
	public BomAction() {
		//两个会话分别会新建例
		logger.trace("start");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITBomService tBomService;
	
	private PageBomBean p = new PageBomBean();

	/*
	 * (non-Javadoc)
	 * @see info.yinhua.web.action.BaseAction#init()
	 */
	@Override
	public String init() {
		
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see info.yinhua.web.action.BaseAction#list()
	 */
	@Override
	public String list() {
		ParamEncoder encoder = new ParamEncoder(getTableId());
		HttpServletRequest request = ServletActionContext.getRequest();
		
		// 页数的参数名
		String sortTypeName = encoder.encodeParameterName(TableTagParameters.PARAMETER_SORTUSINGNAME);
		String pageName = encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String orderName = encoder.encodeParameterName(TableTagParameters.PARAMETER_ORDER);
		String sortName = encoder.encodeParameterName(TableTagParameters.PARAMETER_SORT);

		String sortType = request.getParameter(sortTypeName);
		String page = request.getParameter(pageName);
		String order = request.getParameter(orderName);
		String sort = request.getParameter(sortName);
		
		if (StringUtils.isNumeric(page))
			p.setPageNumber(Integer.parseInt(page));
		p.setSortCriterion(sort);
		if (order != null)
			p.setSortDirection(SortOrderEnum.fromCode(Integer.parseInt(order)));
		
		tBomService.search(p);
		return super.list();
	}
	
	public String transform() throws Exception {
		
		return SUCCESS;
	}

	public String search() {
		

		p.setPageNumber(1);
		tBomService.search(p);
		return SUCCESS;
	}
	
	/**
	 * @return the p
	 */
	public PageBomBean getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(PageBomBean p) {
		this.p = p;
	}

	/**
	 * @return the tBomService
	 */
	public ITBomService gettBomService() {
		return tBomService;
	}

	/**
	 * @param tBomService the tBomService to set
	 */
	public void settBomService(ITBomService tBomService) {
		this.tBomService = tBomService;
	}

}
