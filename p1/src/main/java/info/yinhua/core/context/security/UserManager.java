package info.yinhua.core.context.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import info.yinhua.core.CommonConst;
import info.yinhua.core.context.security.NormalUser;
import info.yinhua.core.data.mapper.NormalUserMapper;
import info.yinhua.web.bean.UserBean;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 代码从以下两个类复制，暂时不用Group，所以删除Group相关代码。
 * @since 2016/07/25
 * @see org.springframework.security.provisioning.JdbcUserDetailsManager
 * @see org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
 */
@Component
public class UserManager extends JdbcDaoImpl implements UserDetailsManager {
	// ~ Static fields/initializers
	// =====================================================================================

	// UserDetailsManager SQL
	public static final String DEF_DELETE_USER_SQL = "delete from users where username = ?";
	public static final String DEF_UPDATE_USER_SQL = "update users set password = ?, enabled = ? where username = ?";
	public static final String DEF_DELETE_USER_AUTHORITIES_SQL = "delete from authorities where username = ?";
	public static final String DEF_USER_EXISTS_SQL = "select username from users where username = ?";
	public static final String DEF_CHANGE_PASSWORD_SQL = "update users set password = ? where username = ?";

	// ~ Instance fields
	// ================================================================================================

	protected final Log logger = LogFactory.getLog(getClass());

	private String deleteUserSql = DEF_DELETE_USER_SQL;
	private String updateUserSql = DEF_UPDATE_USER_SQL;
	private String deleteUserAuthoritiesSql = DEF_DELETE_USER_AUTHORITIES_SQL;
	private String userExistsSql = DEF_USER_EXISTS_SQL;
	private String changePasswordSql = DEF_CHANGE_PASSWORD_SQL;

	private AuthenticationManager authenticationManager;

	private UserCache userCache = new NullUserCache();

	public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled "
			+ "from users " + "where username = ?";
	public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username,authority "
			+ "from authorities " + "where username = ?";
	
	public UserManager() {
		usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
		authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
	}

	@Autowired
	private NormalUserMapper userMapper;
	
	// ~ Methods
	// ========================================================================================================

	protected void initDao() throws ApplicationContextException {
		if (authenticationManager == null) {
			logger.info("No authentication manager set. Reauthentication of users when changing passwords will "
					+ "not be performed.");
		}

		Assert.isTrue(enableAuthorities || enableGroups,
				"Use of either authorities or groups must be enabled");;
	}
	
	//2016/07/28
	public void createUser(UserBean user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(CommonConst.ROLE_USER);
		authorities.add(authority);
		
		NormalUser normalUser = new NormalUser(user.getUsername(), user.getPassword(), authorities);
		createUser(normalUser);
	}

	// ~ UserDetailsManager implementation
	// ==============================================================================

	public void createUser(final UserDetails user) {
		validateUserDetails(user);
		NormalUser normalUser = (NormalUser) user;
		userMapper.create(normalUser);

		if (getEnableAuthorities()) {
			insertUserAuthorities(user);
		}
	}

	public void updateUser(final UserDetails user) {
		validateUserDetails(user);
		getJdbcTemplate().update(updateUserSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getPassword());
				ps.setBoolean(2, user.isEnabled());
				ps.setString(3, user.getUsername());
			}
		});

		if (getEnableAuthorities()) {
			deleteUserAuthorities(user.getUsername());
			insertUserAuthorities(user);
		}

		userCache.removeUserFromCache(user.getUsername());
	}

	private void insertUserAuthorities(UserDetails user) {
		for (GrantedAuthority auth : user.getAuthorities()) {
			userMapper.insertRoles(user.getUsername(), auth.getAuthority());
		}
	}

	public void deleteUser(String username) {
		if (getEnableAuthorities()) {
			deleteUserAuthorities(username);
		}
		getJdbcTemplate().update(deleteUserSql, username);
		userCache.removeUserFromCache(username);
	}

	private void deleteUserAuthorities(String username) {
		getJdbcTemplate().update(deleteUserAuthoritiesSql, username);
	}

	public void changePassword(String oldPassword, String newPassword)
			throws AuthenticationException {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();

		if (currentUser == null) {
			// This would indicate bad coding somewhere
			throw new AccessDeniedException(
					"Can't change password as no Authentication object found in context "
							+ "for current user.");
		}

		String username = currentUser.getName();

		// If an authentication manager has been set, re-authenticate the user with the
		// supplied password.
		if (authenticationManager != null) {
			logger.debug("Reauthenticating user '" + username
					+ "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					username, oldPassword));
		}
		else {
			logger.debug("No authentication manager set. Password won't be re-checked.");
		}

		logger.debug("Changing password for user '" + username + "'");

		getJdbcTemplate().update(changePasswordSql, newPassword, username);

		SecurityContextHolder.getContext().setAuthentication(
				createNewAuthentication(currentUser, newPassword));

		userCache.removeUserFromCache(username);
	}

	protected Authentication createNewAuthentication(Authentication currentAuth,
			String newPassword) {
		UserDetails user = loadUserByUsername(currentAuth.getName());

		UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
				user, null, user.getAuthorities());
		newAuthentication.setDetails(currentAuth.getDetails());

		return newAuthentication;
	}

	public boolean userExists(String username) {
		List<String> users = getJdbcTemplate().queryForList(userExistsSql,
				new String[] { username }, String.class);

		if (users.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(
					"More than one user found with name '" + username + "'", 1);
		}

		return users.size() == 1;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	public void setDeleteUserSql(String deleteUserSql) {
		Assert.hasText(deleteUserSql);
		this.deleteUserSql = deleteUserSql;
	}

	public void setUpdateUserSql(String updateUserSql) {
		Assert.hasText(updateUserSql);
		this.updateUserSql = updateUserSql;
	}

	public void setDeleteUserAuthoritiesSql(String deleteUserAuthoritiesSql) {
		Assert.hasText(deleteUserAuthoritiesSql);
		this.deleteUserAuthoritiesSql = deleteUserAuthoritiesSql;
	}

	public void setUserExistsSql(String userExistsSql) {
		Assert.hasText(userExistsSql);
		this.userExistsSql = userExistsSql;
	}

	public void setChangePasswordSql(String changePasswordSql) {
		Assert.hasText(changePasswordSql);
		this.changePasswordSql = changePasswordSql;
	}

	/**
	 * Optionally sets the UserCache if one is in use in the application. This allows the
	 * user to be removed from the cache after updates have taken place to avoid stale
	 * data.
	 *
	 * @param userCache the cache used by the AuthenticationManager.
	 */
	public void setUserCache(UserCache userCache) {
		Assert.notNull(userCache, "userCache cannot be null");
		this.userCache = userCache;
	}

	private void validateUserDetails(UserDetails user) {
		Assert.hasText(user.getUsername(), "Username may not be empty or null");
		validateAuthorities(user.getAuthorities());
	}

	private void validateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Authorities list must not be null");

		for (GrantedAuthority authority : authorities) {
			Assert.notNull(authority, "Authorities list contains a null entry");
			Assert.hasText(authority.getAuthority(),
					"getAuthority() method must return a non-empty string");
		}
	}
	
	//from JdbcDaoImpl

	protected final MessageSourceAccessor messages = SpringSecurityMessageSource
			.getAccessor();

	private String authoritiesByUsernameQuery;
	private String usersByUsernameQuery;
	private String rolePrefix = "";
	private boolean usernameBasedPrimaryKey = true;
	private boolean enableAuthorities = true;
	private boolean enableGroups;

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	protected String getRolePrefix() {
		return rolePrefix;
	}
	protected boolean getEnableAuthorities() {
		return enableAuthorities;
	}
	public void setEnableAuthorities(boolean enableAuthorities) {
		this.enableAuthorities = enableAuthorities;
	}
	public void setAuthoritiesByUsernameQuery(String queryString) {
		authoritiesByUsernameQuery = queryString;
	}

	protected String getAuthoritiesByUsernameQuery() {
		return authoritiesByUsernameQuery;
	}
	protected void addCustomAuthorities(String username,
			List<GrantedAuthority> authorities) {
	}
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<UserDetails> users = loadUsersByUsername(username);

		if (users.size() == 0) {
			logger.debug("Query returned no results for user '" + username + "'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.notFound", new Object[] { username },
					"Username {0} not found"));
		}

		UserDetails user = users.get(0); // contains no GrantedAuthority[]

		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

		if (enableAuthorities) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
		}

		if (enableGroups) {
//			dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);

		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug("User '" + username
					+ "' has no authorities and will be treated as 'not found'");

			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.noAuthority", new Object[] { username },
					"User {0} has no GrantedAuthority"));
		}

		return createUserDetails(username, user, dbAuths);
	}
	/**
	 * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of UserDetails
	 * objects. There should normally only be one matching user.
	 */
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(usersByUsernameQuery, new String[] { username },
				new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);
						return new NormalUser(username, password, enabled, true, true, true,
								AuthorityUtils.NO_AUTHORITIES);
					}

				});
	}
	
	/**
	 * Loads authorities by executing the SQL from <tt>authoritiesByUsernameQuery</tt>.
	 *
	 * @return a list of GrantedAuthority objects for the user
	 */
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return getJdbcTemplate().query(authoritiesByUsernameQuery,
				new String[] { username }, new RowMapper<GrantedAuthority>() {
					public GrantedAuthority mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String roleName = rolePrefix + rs.getString(2);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}
	
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!usernameBasedPrimaryKey) {
			returnUsername = username;
		}

		return new NormalUser(returnUsername, userFromUserQuery.getPassword(),
				userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities);
	}
}
