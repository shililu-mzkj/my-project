/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.auth.granter;

import lombok.AllArgsConstructor;
import org.springblade.auth.enums.BladeUserEnum;
import org.springblade.auth.utils.TokenUtil;
import org.springblade.chatx.user.entity.Member;
import org.springblade.chatx.user.feign.IMemberClient;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
@Component
@AllArgsConstructor
public class ChatxCodeTokenGranter implements IChatxTokenGranter {

	public static final String GRANT_TYPE = "code";

	private IMemberClient memberClient;

	@Override
	public Member grant(TokenParameter tokenParameter) {
		HttpServletRequest request = WebUtil.getRequest();

//		String code = request.getHeader(TokenUtil.CAPTCHA_HEADER_CODE);

		String code = tokenParameter.getArgs().getStr("code");


		String account = tokenParameter.getArgs().getStr("account");

		R<Boolean> flag = memberClient.checkCode(code, account);

		// 判断验证码
		if (code == null || !flag.getData()) {
			throw new ServiceException(TokenUtil.CAPTCHA_NOT_CORRECT);
		}

		String tenantId = tokenParameter.getArgs().getStr("tenantId");
		String password = tokenParameter.getArgs().getStr("password");

		Member member = null;
		if (Func.isNoneBlank(account, password)) {
			// 获取用户类型
			String userType = tokenParameter.getArgs().getStr("userType");
			R<Member> result;
			// 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
			if (userType.equals(BladeUserEnum.WEB.getName())) {
				result = memberClient.memberInfo(tenantId, account, DigestUtil.encrypt(password));
			} else if (userType.equals(BladeUserEnum.APP.getName())) {
				result = memberClient.memberInfo(tenantId, account, DigestUtil.encrypt(password));
			} else {
				result = memberClient.memberInfo(tenantId, account, DigestUtil.encrypt(password));
			}
			member = result.isSuccess() ? result.getData() : null;
		}
		return member;
	}

}
