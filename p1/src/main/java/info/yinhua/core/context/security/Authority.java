package info.yinhua.core.context.security;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Authority {
	ADMIN, STAFF, USER, GUEST;

	//defalut role (authority)
	public static final String ROLE_USER = USER.name();
	public static final String ROLE_ADMIN = ADMIN.name();

	public static Set<String> getAuthorities(String rolePrefix,
			Collection<? extends GrantedAuthority> grantedAuthorities) {

		Set<String> set = new TreeSet<String>();
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {

			String role = grantedAuthority.getAuthority();
			set.add(getAuthority(role));
		}

		return set;
	}

	public static String getAuthority(String role) {
		String authority = null;

		if (role.endsWith(ADMIN.name())) {
			authority = ADMIN.name();

		} else if (role.endsWith(STAFF.name())) {
			authority = STAFF.name();

		} else if (role.endsWith(USER.name())) {
			authority = USER.name();

		} else if (role.endsWith(GUEST.name())) {
			authority = GUEST.name();
		}

		return authority;
	}

	public static SimpleGrantedAuthority granted(String authority) {
		return new SimpleGrantedAuthority(authority);
	}

	public static Set<String> all() {
		Set<String> set = new LinkedHashSet<>();
		for (Authority a : Authority.values()) {
			set.add(a.name());
		}
		set.remove(GUEST.name());
		return set;
	}
}
