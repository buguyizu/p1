package info.yinhua.web.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

import info.yinhua.core.CommonConst;
import info.yinhua.core.context.security.NormalUser;
import info.yinhua.core.context.security.UserManager;

@Component
public class UserDwr {

	@Autowired
	private UserManager userManager;
	
	@Autowired
    private SessionRegistry sessionRegistry;    
	
	private TextProvider textProvider;
	
	private TextProvider getTextProvider() {
		
		if (textProvider != null)
			return textProvider;

		TextProviderFactory tpf = new TextProviderFactory();
		
		//DelegatingValidatorContext 258
		LocaleProvider localeProvider = new LocaleProvider() {
			
			@Override
			public Locale getLocale() {
				return ActionContext.getContext().getLocale();
			}
			
		};
		ResourceBundle bundle = LocalizedTextUtil.findResourceBundle("message", localeProvider.getLocale());
		textProvider = tpf.createInstance(bundle, localeProvider);
		return textProvider;
	}
	
	/**
	 * logout invalidate-session="false"
	 * 可以实时看到登录状态
	 */
	
	public boolean logged(String username) {
		final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
		for (final Object principal : allPrincipals)
			if (principal instanceof NormalUser)
				if (((NormalUser) principal).getUsername().equals(username))
						return true;

		return false;
	}
	
	public Map<String, String> getUser(String username) {
		Map<String, String> map = new HashMap<String, String>();

		try {

			NormalUser user = (NormalUser) userManager.loadUserByUsername(username);
			map.put("cd", user.getCode());
			map.put("name", user.getName());
			map.put("gender", user.getGender());
			map.put("department", user.getDepartment());
			map.put("comment", user.getComment());
			map.put("version", user.getVersion().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("cd", "-1");
			if (e instanceof UsernameNotFoundException)
				map.put("msg",  getTextProvider().getText(CommonConst.ME_USER_002));
			else
				map.put("msg",  getTextProvider().getText(CommonConst.ME_BACK_001));
		}
		return map;
	}

	public Map<String, String> updateUser(Map<String, String> frontUser) {
		String username = frontUser.get("username"),
				version = frontUser.get("version");
		Map<String, String> map = new HashMap<String, String>();

		try {

			NormalUser user = (NormalUser) userManager.loadUserByUsername(username);
			if (!((NormalUser) user).getVersion().toString().equals(version)) {
				map.put("cd", "1");
				map.put("msg", getTextProvider().getText(CommonConst.ME_USER_001));
			} else {
				user.setCode(frontUser.get("cd"));
				user.setName(frontUser.get("name"));
				user.setGender(frontUser.get("gender"));
				user.setDepartment(frontUser.get("department"));
				user.setComment(frontUser.get("comment"));

				userManager.updateUser(user);

				map.put("cd", "0");
				map.put("msg", getTextProvider().getText(CommonConst.MI_USER_001, new String[] { username }));
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("cd", "-1");
			map.put("msg",  getTextProvider().getText(CommonConst.ME_BACK_001));
		}
		
		return map;
	}
	
	public Map<String, String> removeUser(String username, String version) {
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			UserDetails user = userManager.loadUserByUsername(username);
			
			if ((((NormalUser) user).getVersion().toString()).equals(version)) {
				userManager.deleteUser(username);
				map.put("cd", "0");
				map.put("msg", getTextProvider().getText(CommonConst.MI_USER_003, new String[] { username }));
			} else {
				map.put("cd", "1");
				map.put("msg", getTextProvider().getText(CommonConst.ME_USER_001));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			if (e instanceof UsernameNotFoundException) {
				map.put("cd", "2");
				map.put("msg", getTextProvider().getText(CommonConst.ME_USER_001));
			} else {
				map.put("cd", "-1");
				map.put("msg",  getTextProvider().getText(CommonConst.ME_BACK_001));
			}
		}
		return map;
	}
}
