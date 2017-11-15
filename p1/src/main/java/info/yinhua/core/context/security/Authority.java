package info.yinhua.core.context.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public enum Authority {
	ADMIN,
	STAFF,
	USER;
	
	static List<String> superAuthorities(List<GrantedAuthority> authorities) {
		if (authorities == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (ADMIN.name().equals(grantedAuthority.getAuthority())) {

				if (!list.contains(ADMIN.name())) {
					list.add(ADMIN.name());
				}
				if (!list.contains(STAFF.name())) {
					list.add(STAFF.name());
				}
				if (!list.contains(USER.name())) {
					list.add(USER.name());
				}
			} else if (STAFF.name().equals(grantedAuthority.getAuthority())) {
				if (!list.contains(STAFF.name())) {
					list.add(STAFF.name());
				}
				if (!list.contains(USER.name())) {
					list.add(USER.name());
				}
			} else {
				if (!list.contains(USER.name())) {
					list.add(USER.name());
				}
			}
		}
		return list;
	}
}
