package org.springblade.common.constant;

/**
 * 通用常量
 *
 * @author Chill
 */
public interface CommonConstant {

	/**
	 * sword 系统名
	 */
	String SWORD_NAME = "sword";

	/**
	 * saber 系统名
	 */
	String SABER_NAME = "saber";

	/**
	 * 顶级父节点id
	 */
	Long TOP_PARENT_ID = 0L;

	/**
	 * 顶级父节点名称
	 */
	String TOP_PARENT_NAME = "顶级";


	/**
	 * 默认密码
	 */
	String DEFAULT_PASSWORD = "123456";


	String APPLICATION_CHATX_USER_NAME = "chatx-user";

	String APPLICATION_CLIENT = "chatx";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "chatx_code_";

	/**
	 * 验证码长度
	 */
	String CODE_SIZE = "4";

	/**
	 * 验证码有效期
	 */
	int CODE_TIME = 60;

	String SYSTEM_HEADER = "chatx-system";

	String SYSTEM_HEADER_API ="chat-client";

	String CHATX_HEADER = "chatx-auth";


}
