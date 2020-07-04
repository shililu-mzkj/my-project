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

package org.springblade.chatx.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.chatx.user.service.IMobileService;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author shililu
 * @date 2018/11/14
 * <p>
 * 手机验证码
 */
//@RestController
//@AllArgsConstructor
//@RequestMapping("/mobile")
//@Api(value = "mobile", tags = "手机验证码")
//public class MobileController {
//	private final IMobileService mobileService;
//
//
//	@GetMapping("/{mobile}")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "sendType", value = "发送类型:1:登录 2:注册 3:忘记密码", required = true, dataType = "String", paramType = "query"),
//		@ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String", paramType = "query")
//	})
//	@ApiOperation(value = "验证码发送", notes = "验证码发送")
//	public R sendSmsCode(@PathVariable String mobile, @RequestParam Integer sendType) {
//		return mobileService.sendSmsCode(mobile,sendType);
//	}
//
//
//
//	@GetMapping("/checkCode/{mobile}")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "code", required = true, dataType = "String", paramType = "query"),
//		@ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String", paramType = "query")
//	})
//	@ApiOperation(value = "验证码发送", notes = "验证码发送")
//	public R checkCode(@PathVariable String mobile, @RequestParam String code) {
//		return mobileService.checkCode(mobile,code);
//	}
//}
