package info.yinhua.core.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import info.yinhua.core.CommonConst;

//https://murygin.wordpress.com/2010/04/23/parameter-substitution-in-resource-bundles/
public class Messages {
	
	private static final String BUNDLE_NAME = "message";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, CommonConst.LOCALE);

	/** 认证失败 */
	public static final String ME_LOGIN_001 = "ME-LOGIN-001";
	public static final String ME_LOGIN_002 = "ME-LOGIN-002";
	public static final String ME_LOGIN_003 = "ME-LOGIN-003";
	public static final String ME_LOGIN_004 = "ME-LOGIN-004";
	public static final String ME_LOGIN_005 = "ME-LOGIN-005";
	/** 重新登录 */
	public static final String ME_LOGIN_011 = "ME-LOGIN-011";
	
	public static final String ME_INPUT_001 = "ME-INPUT-001";
	public static final String ME_INPUT_002 = "ME-INPUT-002";
	public static final String ME_INPUT_003 = "ME-INPUT-003";
	public static final String ME_INPUT_004 = "ME-INPUT-004";
	public static final String ME_INPUT_005 = "ME-INPUT-005";
	public static final String ME_INPUT_006 = "ME-INPUT-006";

	public static final String ME_USER_001 = "ME-USER-001";
	public static final String ME_USER_002 = "ME-USER-002";

	public static final String ME_BACK_001 = "ME-BACK-001";

	/** 用户已存在 */
	public static final String ME_SIGNUP_001 = "ME-SIGNUP-001";

	public static final String MW_USER_002 = "MW-USER-002";
	
	/** 注册成功 */
	public static final String MI_SIGNUP_001 = "MI-SIGNUP-001";
	public static final String MI_USER_001 = "MI-USER-001";
	public static final String MI_USER_002 = "MI-USER-002";
	public static final String MI_USER_003 = "MI-USER-003";

	public static final String MC_AUTHORITY_001 = "MC-AUTHORITY-001";

	/** 会话 */
	public static final String MI_SESSION_001 = "MI-SESSION-001";
    
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
        	e.printStackTrace();
            return '!' + key + '!';
        }
    }
    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
        	e.printStackTrace();
            return '!' + key + '!';
        }
    }
}
