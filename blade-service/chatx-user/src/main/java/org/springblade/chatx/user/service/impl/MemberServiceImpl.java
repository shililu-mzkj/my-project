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
package org.springblade.chatx.user.service.impl;

import org.springblade.chatx.user.entity.Member;
import org.springblade.chatx.user.vo.MemberVO;
import org.springblade.chatx.user.mapper.MemberMapper;
import org.springblade.chatx.user.service.IMemberService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author Blade
 * @since 2020-06-30
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements IMemberService {

	@Override
	public IPage<MemberVO> selectMemberPage(IPage<MemberVO> page, MemberVO member) {
		return page.setRecords(baseMapper.selectMemberPage(page, member));
	}


	@Override
	public Member userInfo(String tenantId, String account, String password) {
		return baseMapper.getUser(tenantId,account,password);
	}
}
