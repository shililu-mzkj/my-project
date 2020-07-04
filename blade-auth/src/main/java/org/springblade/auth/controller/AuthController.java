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
package org.springblade.auth.controller;

import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.auth.granter.*;
import org.springblade.auth.utils.TokenUtil;
import org.springblade.chatx.user.entity.Member;
import org.springblade.chatx.user.feign.IMemberClient;
import org.springblade.common.cache.CacheNames;
import org.springblade.core.secure.AuthInfo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.RedisUtil;
import org.springblade.core.tool.utils.WebUtil;
import org.springblade.system.user.entity.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 认证模块
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@Api(value = "用户授权认证", tags = "授权接口")
public class AuthController {

	private RedisUtil redisUtil;

	private IMemberClient memberClient;

	@PostMapping("token")
	@ApiOperation(value = "获取认证token", notes = "传入租户ID:tenantId,账号:account,密码:password")
	public R<AuthInfo> token(@ApiParam(value = "授权类型", required = true) @RequestParam(defaultValue = "password", required = false) String grantType,
							 @ApiParam(value = "刷新令牌") @RequestParam(required = false) String refreshToken,
							 @ApiParam(value = "租户ID", required = true) @RequestParam(defaultValue = "000000", required = false) String tenantId,
							 @ApiParam(value = "账号") @RequestParam(required = false) String account,
							 @ApiParam(value = "密码") @RequestParam(required = false) String password) {

		String userType = Func.toStr(WebUtil.getRequest().getHeader(TokenUtil.USER_TYPE_HEADER_KEY), TokenUtil.DEFAULT_USER_TYPE);

		TokenParameter tokenParameter = new TokenParameter();
		tokenParameter.getArgs().set("tenantId", tenantId)
			.set("account", account)
			.set("password", password)
			.set("grantType", grantType)
			.set("refreshToken", refreshToken)
			.set("userType", userType);

		ITokenGranter granter = TokenGranterBuilder.getGranter(grantType);
		UserInfo userInfo = granter.grant(tokenParameter);

		if (userInfo == null || userInfo.getUser() == null || userInfo.getUser().getId() == null) {
			return R.fail(TokenUtil.USER_NOT_FOUND);
		}

		return R.data(TokenUtil.createAuthInfo(userInfo));
	}

	@GetMapping("/captcha")
	@ApiOperation(value = "获取验证码")
	public R<Kv> captcha() {
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
		String verCode = specCaptcha.text().toLowerCase();
		String key = UUID.randomUUID().toString();
		// 存入redis并设置过期时间为30分钟
		redisUtil.set(CacheNames.CAPTCHA_KEY + key, verCode, 30L, TimeUnit.MINUTES);
		// 将key和base64返回给前端
		return R.data(Kv.init().set("key", key).set("image", specCaptcha.toBase64()));
	}


	@PostMapping("/chatx/token")
	@ApiOperation(value = "短信验证码登录", notes = "传入租户ID:tenantId,账号:account,密码:password")
	public R<AuthInfo> chatxToken(@ApiParam(value = "授权类型", required = true) @RequestParam(defaultValue = "password", required = false) String grantType,
								  @ApiParam(value = "刷新令牌") @RequestParam(required = false) String refreshToken,
								  @ApiParam(value = "租户ID", required = true) @RequestParam(defaultValue = "000000", required = false) String tenantId,
								  @ApiParam(value = "账号") @RequestParam(required = false) String account,
								  @ApiParam(value = "code") @RequestParam(required = false) String code,
								  @ApiParam(value = "密码") @RequestParam(required = false) String password) {


		String userType = Func.toStr(WebUtil.getRequest().getHeader(TokenUtil.USER_TYPE_HEADER_KEY), TokenUtil.DEFAULT_USER_TYPE);

		TokenParameter tokenParameter = new TokenParameter();
		tokenParameter.getArgs().set("tenantId", tenantId)
			.set("account", account)
			.set("password", password)
			.set("code",code)
			.set("grantType", grantType)
			.set("refreshToken", refreshToken)
			.set("userType", userType);

		IChatxTokenGranter granter = ChatxTokenGranterBuilder.getGranter(grantType);
		Member member = granter.grant(tokenParameter);

		if (member == null || member.getId() == null) {
			return R.fail(TokenUtil.USER_NOT_FOUND);
		}

		return R.data(TokenUtil.createApiAuthInfo(member));
	}

	@GetMapping("/smscode")
	@ApiOperation(value = "获取短信验证码--发送类型:1:登录 2:注册 3:忘记密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sendType", value = "发送类型:1:登录 2:注册 3:忘记密码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String", paramType = "query")
	})
	public R<String> sendSmsCode(@RequestParam String mobile, @RequestParam Integer sendType) {
		R result=memberClient.sendCode(mobile,sendType);
		String ret =result.isSuccess() ? result.getMsg().toString() : null;
		return R.data(ret);
	}

}
