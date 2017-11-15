package info.yinhua.core.context.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import info.yinhua.core.CommonConst;
import info.yinhua.core.data.mapper.NormalUserMapper;
import info.yinhua.core.data.mapper.TMenuMapper;
import info.yinhua.core.data.model.TLog;
import info.yinhua.core.data.model.TMenu;
import info.yinhua.core.service.TLogService;
import info.yinhua.web.bean.UserBean;

/**
 * 代码从以下两个类复制，暂时不用Group，所以删除Group相关代码。
 * @since 2016/07/25
 * @see org.springframework.security.provisioning.JdbcUserDetailsManager
 * @see org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
 * 
 * http://docs.spring.io/autorepo/docs/spring/4.2.x/spring-framework-reference/html/transaction.html#transaction-declarative-annotations
 * http://stackoverflow.com/questions/23132822/what-is-the-difference-between-defining-transactional-on-class-vs-method
 */
@Service
public class UserManager implements UserDetailsManager {

	// ~ Instance fields
	// ================================================================================================

	protected final Log logger = LogFactory.getLog(getClass());

	private AuthenticationManager authenticationManager;
	private UserCache userCache = new NullUserCache();

	@Autowired
	private NormalUserMapper userMapper;
	@Autowired
	private TMenuMapper tMenuMapper;
	@Autowired
	private TLogService logService;
	
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

	// ~ UserDetailsManager implementation
	// ==============================================================================
	@Override
	@Transactional
	public void createUser(UserDetails user) {
		Assert.isInstanceOf(UserBean.class, user);
		validateUserDetails(user);

		NormalUser normalUser = new NormalUser(user.getUsername(),
				user.getPassword(),
				user.isEnabled(),
				user.isAccountNonExpired(),
				user.isCredentialsNonExpired(),
				user.isAccountNonLocked(),
				user.getAuthorities());
		normalUser.setCreateUser(CommonConst.USER_SYSTEM);
		normalUser.setUpdateUser(CommonConst.USER_SYSTEM);
		
		userMapper.create(normalUser);

		if (getEnableAuthorities()) {
			insertUserAuthorities(user);
		}
		
		TLog log = new TLog();
		log.setLogType(CommonConst.LogType.USER_REGIST.type());
		log.setLogContent(CommonConst.LogType.USER_REGIST.content());
		Map<String, String> param = new HashMap<String, String>();
		param.put(CommonConst.LOG_PARAM_USERNAME, normalUser.getUsername());
		log.setLogParam(param.toString());
		
		logService.create(log);
	}

	@Override
	@Transactional
	public void updateUser(UserDetails user) {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();

		if (currentUser == null) {
			// This would indicate bad coding somewhere
			throw new AccessDeniedException(
					"Can't update user as no Authentication object found in context "
							+ "for current user.");
		}

		validateUserDetails(user);
		if (user instanceof UserBean) {
	
			NormalUser normalUser = new NormalUser(user.getUsername(),
					user.getPassword(),
					user.isEnabled(),
					user.isAccountNonExpired(),
					user.isCredentialsNonExpired(),
					user.isAccountNonLocked(),
					user.getAuthorities());
	
			normalUser.setIdNumber(((UserBean) user).getIdNumber());
			normalUser.setCode(((UserBean) user).getCode());
			normalUser.setName(((UserBean) user).getName());
			normalUser.setGender(((UserBean) user).getGender());
			normalUser.setDepartment(((UserBean) user).getDepartment());
			normalUser.setComment(((UserBean) user).getComment());
			normalUser.setUpdateUser(user.getUsername());
			userMapper.update(normalUser);
		} else if (user instanceof NormalUser) {
			userMapper.update((NormalUser) user);
		}
		
		if (getEnableAuthorities()) {
			userMapper.deleteRoles(user.getUsername());
			insertUserAuthorities(user);
		}

		SecurityContextHolder.getContext().setAuthentication(
				createNewAuthentication(currentUser, null));
		
		userCache.removeUserFromCache(user.getUsername());
//http://stackoverflow.com/questions/4664893/how-to-manually-set-an-authenticated-user-in-spring-security-springmvc/4672083#4672083
//http://stackoverflow.com/questions/892733/how-to-immediately-enable-the-authority-after-update-user-authority-in-spring-se

		TLog log = new TLog();
		log.setLogType(CommonConst.LogType.USER_EDIT.type());
		log.setLogContent(CommonConst.LogType.USER_EDIT.content());
		Map<String, String> param = new HashMap<String, String>();
		param.put(CommonConst.LOG_PARAM_USERNAME, user.getUsername());
		log.setLogParam(param.toString());
		
		logService.create(log);
	}

	private void insertUserAuthorities(UserDetails user) {
		for (GrantedAuthority auth : user.getAuthorities()) {
			userMapper.insertRoles(user.getUsername(), auth.getAuthority());
		}
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		if (getEnableAuthorities()) {
			userMapper.deleteRoles(username);
		}
		userMapper.delete(username);
		userCache.removeUserFromCache(username);
		
		TLog log = new TLog();
		log.setLogType(CommonConst.LogType.USER_DELETE.type());
		log.setLogContent(CommonConst.LogType.USER_DELETE.content());
		Map<String, String> param = new HashMap<String, String>();
		param.put(CommonConst.LOG_PARAM_USERNAME, username);
		log.setLogParam(param.toString());
		
		logService.create(log);
	}

	@Override
	@Transactional
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
		userMapper.changePassword(username, newPassword);

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
		
		Collection<String> users = userMapper.exists(username);

		if (users.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(
					"More than one user found with name '" + username + "'", 1);
		}

		return users.size() == 1;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
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
	protected boolean getEnableGroups() {
		return enableGroups;
	}
	public void setEnableGroups(boolean enableGroups) {
		this.enableGroups = enableGroups;
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
		List<Map<String, Object>> users = userMapper.getUsers(username);
		List<UserDetails> userList = new ArrayList<UserDetails>();
		if (users != null) {
			for (Map<String, Object> user : users) {
				NormalUser normalUser = new NormalUser(
						(String) user.get("USERNAME"),
						(String) user.get("PASSWORD"),
						((Boolean) user.get("ENABLED")).booleanValue(),
						true,
						true,
						true,
						AuthorityUtils.NO_AUTHORITIES);
				normalUser.setCode((String) user.get("CODE"));
				normalUser.setName((String) user.get("NAME"));
				normalUser.setGender((String) user.get("GENDER"));
				normalUser.setDepartment((String) user.get("DEPARTMENT"));
				normalUser.setComment((String) user.get("COMMENT"));
				normalUser.setVersion((Integer) user.get("VERSION"));
				userList.add(normalUser);
			}
		}
		
		return userList;
	}

	/**
	 * Loads authorities by executing the SQL from <tt>authoritiesByUsernameQuery</tt>.
	 *
	 * @return a list of GrantedAuthority objects for the user
	 */
	protected List<GrantedAuthority> loadUserAuthorities(String username) {

		List<Map<String, Object>> roles = userMapper.getRoles(username);
		List<GrantedAuthority> rolesList = new ArrayList<GrantedAuthority>();
		if (roles != null) {
			for (Map<String, Object> role : roles) {
				rolesList.add(new SimpleGrantedAuthority(rolePrefix + (String) role.get("AUTHORITY")));
			}
		}
		
		return rolesList;
	}
	
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!usernameBasedPrimaryKey) {
			returnUsername = username;
		}

		NormalUser normalUser = new NormalUser(returnUsername, userFromUserQuery.getPassword(),
				userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities);

		Assert.isInstanceOf(NormalUser.class, userFromUserQuery);
		NormalUser normalUserFromUserQuery = (NormalUser) userFromUserQuery;
		normalUser.setIdNumber(normalUserFromUserQuery.getIdNumber());
		normalUser.setCode(normalUserFromUserQuery.getCode());
		normalUser.setName(normalUserFromUserQuery.getName());
		normalUser.setGender(normalUserFromUserQuery.getGender());
		normalUser.setDepartment(normalUserFromUserQuery.getDepartment());
		normalUser.setComment(normalUserFromUserQuery.getComment());
		normalUser.setVersion(normalUserFromUserQuery.getVersion());
		List<String> authorityList = Authority.superAuthorities(combinedAuthorities);
		// get menu list form db
		List<TMenu> menuList = tMenuMapper.getList(authorityList);

		// generate group-menu list
		List<TMenu> hMenuList =  new ArrayList<TMenu>();
		List<TMenu> bMenuList =  new ArrayList<TMenu>();
		for (TMenu menu : menuList) {
			if (menu.getMenuCode().endsWith("0")) {
				hMenuList.add(menu);
			} else {
				bMenuList.add(menu);
			}
		}

		List<Map<String, List<TMenu>>> menuMapList = new ArrayList<Map<String, List<TMenu>>>();
		HashMap<String, List<TMenu>> menuMap = null;
		for (TMenu hMenu : hMenuList) {

			menuMap = new HashMap<String, List<TMenu>>();
			
			List<TMenu> hbMenuList = new ArrayList<TMenu>();
			hbMenuList.add(hMenu);
			menuMap.put("h", hbMenuList);

			hbMenuList = new ArrayList<TMenu>();

			String h = hMenu.getMenuCode().substring(0, 1);
			for (TMenu bMenu : bMenuList) {
				if (bMenu.getMenuCode().startsWith(h)) {
					hbMenuList.add(bMenu);
				}
			}
			menuMap.put("b", hbMenuList);
			menuMapList.add(menuMap);
		}
		
		normalUser.setMenuList(menuMapList);

		return normalUser;
	}
}
