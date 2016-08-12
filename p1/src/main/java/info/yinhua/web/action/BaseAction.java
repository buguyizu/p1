package info.yinhua.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;

import com.opensymphony.xwork2.ActionSupport;

import info.yinhua.core.CommonConst;
import info.yinhua.core.data.model.MCode;
import info.yinhua.web.service.ICommonService;
import info.yinhua.web.service.IMCodeService;

@EnableCaching()
public abstract class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public static final String HOME = "home";
	protected String source;
	protected String error;
	private String _csrf;

	@Autowired
	protected ICommonService commonService;
	@Autowired
	protected IMCodeService mCodeService;
	
	public List<MCode> getCodeList(String type) {
		return mCodeService.getList(type);
	}
	
	public String getCodeText(CommonConst.Code code, String cd) {
		return getCodeText(code.getCode(), cd);
	}
	
	public String getCodeText(String type, String cd) {
		MCode code = mCodeService.getCode(type, cd);
		return code == null ? "" : code.getfValue();
	}
	
	//初期化
	public abstract String init() throws Exception;


	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String get_csrf() {
		return _csrf;
	}

	public void set_csrf(String _csrf) {
		this._csrf = _csrf;
	}
}
