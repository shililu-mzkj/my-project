/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.chatx.user.feign;

import lombok.AllArgsConstructor;


import org.springblade.chatx.user.entity.Member;
import org.springblade.chatx.user.service.IMemberService;
import org.springblade.chatx.user.service.IMobileService;
import org.springblade.core.tool.api.R;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务Feign实现类
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
public class MemberClient implements IMemberClient {

	private IMemberService service;

	private IMobileService mobileService;



	@Override
	public R<Member> memberInfo(Long memberId) {
		return R.data(service.getById(memberId));
	}

//	@GetMapping(API_PREFIX + "/member-info")
	@Override
	public R<Member> memberInfo(String tenantId, String account, String password) {
		return R.data(service.userInfo(tenantId,account,password));
	}

	@Override
//	@GetMapping(MOBILE_API_PREFIX + "/{mobile}")
	public R sendCode(@PathVariable String mobile, @RequestParam Integer sendType) {
		return mobileService.sendSmsCode(mobile,sendType);
	}

	@Override
	public R<Boolean> checkCode( @RequestParam String code, @PathVariable String mobile) {
		return mobileService.checkCode(code,mobile);
	}


}
