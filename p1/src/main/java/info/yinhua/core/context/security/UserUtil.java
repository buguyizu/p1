package info.yinhua.core.context.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import info.yinhua.core.data.model.TMenu;

public class UserUtil {

	// get username of current session
	public static String getUsername() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {

			Object principal = auth.getPrincipal();

			if (principal instanceof String) {

				return (String) principal;

			} else if (principal instanceof UserDetails) {

				return ((UserDetails) principal).getUsername();

			} else {

				return null;
			}

		} else {

			return null;
		}
	}

	public static Collection<GrantedAuthority> getAuthorities() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {

			Object principal = auth.getPrincipal();
			if (principal instanceof NormalUser) {
				return ((NormalUser) principal).getAuthorities();
			}
		}
		
		return null;
	}

	public static List<Map<String, List<TMenu>>> getMenuList() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {

			Object principal = auth.getPrincipal();
			if (principal instanceof NormalUser) {
				return ((NormalUser) principal).getMenuList();
			}
		}
		
		return null;
	}
}
