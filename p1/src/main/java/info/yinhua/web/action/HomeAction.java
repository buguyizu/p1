package info.yinhua.web.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller
public class HomeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String init() throws Exception {
		
		ActionContext.getContext().put("title", getText("title"));
		return SUCCESS;
//		throw new Exception();
	}

}
