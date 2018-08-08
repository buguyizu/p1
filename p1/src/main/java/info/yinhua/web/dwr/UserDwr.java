package info.yinhua.web.dwr;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import info.yinhua.core.context.security.Authority;
import info.yinhua.core.context.security.NormalUser;
import info.yinhua.core.context.security.UserManager;
import info.yinhua.core.service.UserListService;
import info.yinhua.core.util.Messages;

@Component
public class UserDwr {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserListService userService;

	@Autowired
	private SessionRegistry sessionRegistry;

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
				map.put("msg",  Messages.getString(Messages.ME_USER_002));
			else
				map.put("msg",  Messages.getString(Messages.ME_BACK_001));
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
				map.put("msg", Messages.getString(Messages.ME_USER_001));
			} else {
				user.setCode(frontUser.get("cd"));
				user.setName(frontUser.get("name"));
				user.setGender(frontUser.get("gender"));
				user.setDepartment(frontUser.get("department"));
				user.setComment(frontUser.get("comment"));

				userManager.updateUser(user);

				map.put("cd", "0");
				map.put("msg", Messages.getString(Messages.MI_USER_001, username));
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("cd", "-1");
			map.put("msg",  Messages.getString(Messages.ME_BACK_001));
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
				map.put("msg", Messages.getString(Messages.MI_USER_003, username));
			} else {
				map.put("cd", "1");
				map.put("msg", Messages.getString(Messages.ME_USER_001));
			}

		} catch (Exception e) {
			e.printStackTrace();

			if (e instanceof UsernameNotFoundException) {
				map.put("cd", "2");
				map.put("msg", Messages.getString(Messages.ME_USER_001));
			} else {
				map.put("cd", "-1");
				map.put("msg",  Messages.getString(Messages.ME_BACK_001));
			}
		}
		return map;
	}

	public String[] users(String username) {
		if (StringUtils.isBlank(username)) {
			return new String[0];
		}
		Collection<String> users = userService.getUsers(username);
		
		return users.toArray(new String[users.size()]);
	}

	public Map<String, String> authorities(String[] users) {

		Map<String, String> map = new LinkedHashMap<>();
		if (users != null) {
			Set<String> all = Authority.all();
			if (users.length > 1) {
				for (String a : all) {
					map.put(a, "-1");
				}
			} else {
				NormalUser user = (NormalUser) userManager.loadUserByUsername(users[0]);
				Collection<GrantedAuthority> collection = user.getAuthorities();

				Set<String> authorities = new LinkedHashSet<>();
				for (GrantedAuthority a : collection) {
					String role = Authority.getAuthority(a.getAuthority());
					authorities.add(role);
				}

				// 1: has the role; 0: has no; -2: disable
				for (String a : all) {
					if (authorities.contains(a)) {
						map.put(a, "1");
					} else {
						map.put(a, "0");
					}
				}
			}
			map.put(Authority.ROLE_USER, "-2");
		}

		return map;
	}

	public Map<String, String> operate(String[] users, String authority, String opFlg) {
		Map<String, String> map = new HashMap<String, String>();
		int count = 0;
		if (users != null) {
			Set<String> all = Authority.all();
			if (all.contains(authority) && opFlg != null && opFlg.matches("\\-?\\d")) {
				for (String user : users) {
					if (Authority.ROLE_ADMIN.equals(user.toUpperCase()))
						continue;
					int i = userManager.updateAuthorities(user, authority, Integer.parseInt(opFlg));
					if (i > -1)
						count += i;
				}
			}
		}
		map.put("count", String.valueOf(count));
		return map;
	}
}
