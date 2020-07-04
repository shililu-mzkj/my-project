/*
 *    Copyright (c) 2018-2025, shililu All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: shililu (wangiegie@gmail.com)
 */

package org.springblade.chatx.user.service;


import org.springblade.chatx.user.entity.Member;
import org.springblade.core.tool.api.R;

/**
 * @author shililu
 * @date 2018/11/14
 */
public interface IMobileService {
	/**
	 * 发送手机验证码
	 *
	 * @param mobile mobile
	 * @return code
	 */
	R sendSmsCode(String mobile, Integer sendType);

	/**
	 * 校验验证码
	 * @param code
	 * @param randomStr
	 * @throws Exception
	 */
	R<Boolean> checkCode(String code, String randomStr);



}
