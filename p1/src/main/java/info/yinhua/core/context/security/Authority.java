package info.yinhua.core.context.security;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;

public enum Authority {
	ADMIN, STAFF, USER;

	public static Set<String> getAuthorities(String rolePrefix,
			Collection<? extends GrantedAuthority> grantedAuthorities) {

		Set<String> set = new TreeSet<String>();
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			String role = grantedAuthority.getAuthority();
			
			if (role.endsWith(ADMIN.name())) {
				set.add(ADMIN.name());
			} else if (role.endsWith(STAFF.name())) {
				set.add(STAFF.name());
			} else if (role.endsWith(USER.name())) {
				set.add(USER.name());
			}
		}

		return set;
	}
}
