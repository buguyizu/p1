package info.yinhua.core.context.security;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;

public enum Authority {
	ADMIN,
	STAFF,
	USER;
	
	static Set<String> superAuthorities(List<GrantedAuthority> authorities) {
		if (authorities == null) {
			return null;
		}
		Set<String> set = new TreeSet<String>();
		for (GrantedAuthority grantedAuthority : authorities) {

			if (grantedAuthority.getAuthority().endsWith(ADMIN.name())) {
				set.add(ADMIN.name());
				set.add(STAFF.name());
				set.add(USER.name());

			} else if (grantedAuthority.getAuthority().endsWith(STAFF.name())) {
				set.add(STAFF.name());
				set.add(USER.name());

			} else {
				set.add(USER.name());
			}
		}
		return set;
	}
}
