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

package org.springblade.chatx.user.service.impl;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.chatx.user.entity.Member;
import org.springblade.chatx.user.mapper.MemberMapper;
import org.springblade.chatx.user.service.IMobileService;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.constant.enums.LoginTypeEnum;
import org.springblade.common.tool.RandomUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shililu
 * @date 2018/11/14
 * <p>
 * 手机登录相关业务实现
 */
@Slf4j
@Service
@AllArgsConstructor
public class MobileServiceImpl implements IMobileService {
	private final RedisTemplate redisTemplate;
	private final MemberMapper memberMapper;


	/**
	 * 发送手机验证码
	 * TODO: 调用短信网关发送验证码,测试返回前端
	 *
	 * @param mobile mobile
	 * @return code
	 */
	@Override
	public R sendSmsCode(String mobile, Integer sendType) {
		if (sendType == 1) {
			List<Member> userList = memberMapper.selectList(Wrappers
				.<Member>query().lambda()
				.eq(Member::getMobile, mobile));

			if (Func.isEmpty(userList)) {
				log.info("手机号未注册:{}", mobile);
				return R.fail("手机号未注册");
			}
		}

		Object codeObj = redisTemplate.opsForValue().get(CommonConstant.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);

		if (codeObj != null) {
			log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
			return R.fail("验证码发送过频繁");
		}

		String code = RandomUtil.randomNumbers(Integer.parseInt(CommonConstant.CODE_SIZE));
		log.debug("手机号生成验证码成功:{},{}", mobile, code);
		redisTemplate.opsForValue().set(
			CommonConstant.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile
			, code, CommonConstant.CODE_TIME, TimeUnit.SECONDS);
		return R.success(code);
	}

	@Override
	public R<Boolean> checkCode(String code, String mobile) {
		boolean checkFlag = true;

		if (Func.isBlank(code)) {
			checkFlag = false;
			return R.data(checkFlag);

		}

		String key = CommonConstant.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT+ mobile;
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		if (!redisTemplate.hasKey(key)) {
			checkFlag = false;
			return R.data(checkFlag);


		}

		Object codeObj = redisTemplate.opsForValue().get(key);

		if (codeObj == null) {
			checkFlag = false;
			return R.data(checkFlag);

		}

		String saveCode = codeObj.toString();
		if (Func.isBlank(saveCode)) {
			redisTemplate.delete(key);
			checkFlag = false;
			return R.data(checkFlag);
		}

		if (!Func.equals(saveCode, code)) {
			redisTemplate.delete(key);
			checkFlag = false;
			return R.data(checkFlag);

		}

		if(checkFlag) {
			redisTemplate.delete(key);
		}
		return R.data(checkFlag);

	}

}
