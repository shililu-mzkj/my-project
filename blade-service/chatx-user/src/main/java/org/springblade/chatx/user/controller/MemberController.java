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
package org.springblade.chatx.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.chatx.user.entity.Member;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.chatx.user.vo.MemberVO;
import org.springblade.chatx.user.wrapper.MemberWrapper;
import org.springblade.chatx.user.service.IMemberService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 *  控制器
 *
 * @author Blade
 * @since 2020-06-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/member")
@Api(value = "", tags = "接口")
public class MemberController extends BladeController {

	private IMemberService memberService;

	/**
	* 详情
	*/
	@GetMapping("/detail")
    @ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入member")
	public R<MemberVO> detail(Member member) {
		Member detail = memberService.getOne(Condition.getQueryWrapper(member));
		return R.data(MemberWrapper.build().entityVO(detail));
	}

	/**
	* 分页
	*/
	@GetMapping("/list")
    @ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入member")
	public R<IPage<MemberVO>> list(Member member, Query query) {
		IPage<Member> pages = memberService.page(Condition.getPage(query), Condition.getQueryWrapper(member));
		return R.data(MemberWrapper.build().pageVO(pages));
	}

	/**
	* 自定义分页
	*/
	@GetMapping("/page")
    @ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入member")
	public R<IPage<MemberVO>> page(MemberVO member, Query query) {
		IPage<MemberVO> pages = memberService.selectMemberPage(Condition.getPage(query), member);
		return R.data(pages);
	}

	/**
	* 新增
	*/
	@PostMapping("/save")
    @ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入member")
	public R save(@Valid @RequestBody Member member) {
		return R.status(memberService.save(member));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
    @ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入member")
	public R update(@Valid @RequestBody Member member) {
		return R.status(memberService.updateById(member));
	}

	/**
	* 新增或修改
	*/
	@PostMapping("/submit")
    @ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入member")
	public R submit(@Valid @RequestBody Member member) {
		return R.status(memberService.saveOrUpdate(member));
	}


	/**
	* 删除
	*/
	@PostMapping("/remove")
    @ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(memberService.deleteLogic(Func.toLongList(ids)));
	}


}
