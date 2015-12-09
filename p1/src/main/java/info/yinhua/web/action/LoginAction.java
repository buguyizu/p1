package info.yinhua.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import info.yinhua.core.db.model.MCode;
import info.yinhua.web.service.IMCodeService;

@Controller
public class LoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<MCode> list;

	private String user;
	private String password;

	@Autowired
	private IMCodeService mCodeService;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		com.opensymphony.sitemesh.webapp.SiteMeshFilter
		
		//org.sitemesh.config.ConfigurableSiteMeshFilter
//		org.apache.struts2.tiles.StrutsTilesListener
		
		return "success";
	}


	public String login() throws Exception {
		
//		String s = mCodeService.getName(3);
//		LOG.debug(s);
		setList(mCodeService.getList("11"));
		
		
		return "success";
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the mCodeService
	 */
	public IMCodeService getmCodeService() {
		return mCodeService;
	}


	/**
	 * @param mCodeService the mCodeService to set
	 */
	public void setmCodeService(IMCodeService mCodeService) {
		this.mCodeService = mCodeService;
	}


	/**
	 * @return the list
	 */
	public List<MCode> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<MCode> list) {
		this.list = list;
	}


	@Override
	public String init() {
		// TODO Auto-generated method stub
		return null;
	}

}
