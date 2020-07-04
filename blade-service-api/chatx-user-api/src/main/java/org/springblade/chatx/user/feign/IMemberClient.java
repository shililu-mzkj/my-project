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


import org.springblade.chatx.user.entity.Member;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User Feign接口类
 *
 * @author Chill
 */
@FeignClient(
	value = "chatx-user",
	fallback = IMemberClientFallback.class
)
public interface IMemberClient {

	String API_PREFIX = "/member";

	String MOBILE_API_PREFIX = "/mobile";

	/**
	 * 获取用户信息
	 *
	 * @param memberId 用户id
	 * @return
	 */
	@GetMapping(API_PREFIX + "/member-info-by-id")
	R<Member> memberInfo(@RequestParam("memberId") Long memberId);

	/**
	 * 获取用户信息
	 *
	 * @param tenantId 租户ID
	 * @param account    账号
	 * @param password   密码
	 * @return
	 */
	@GetMapping(API_PREFIX + "/member-info")
	R<Member> memberInfo(@RequestParam("tenantId") String tenantId, @RequestParam("mobile") String account, @RequestParam("password") String password);


	@GetMapping(MOBILE_API_PREFIX + "/{mobile}")
	R sendCode(@PathVariable String mobile,@RequestParam("sendType") Integer sendType);

	@GetMapping(MOBILE_API_PREFIX + "/checkCode/{mobile}")
	R<Boolean> checkCode(@RequestParam("code") String code, @PathVariable String mobile);

}
