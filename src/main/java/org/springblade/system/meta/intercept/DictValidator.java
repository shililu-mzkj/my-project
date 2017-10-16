/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.system.meta.intercept;

import org.springblade.core.aop.Invocation;
import org.springblade.core.intercept.BladeValidator;
import org.springblade.core.plugins.dao.Blade;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.kit.StrKit;
import org.springblade.system.model.Dict;

/**
 * DictValidator
 * @author zhuangqian
 */
public class DictValidator extends BladeValidator {

	@Override
	protected void doValidate(Invocation inv) {
		validateDict("该字典序号已存在!");
	}

	protected void validateDict(String errorMessage) {
		String num = request.getParameter("blade_dict.num");
		if (StrKit.notBlank(num)) {
			String code = "";
			String id = request.getParameter("blade_dict.id");
			if (StrKit.notBlank(id)) {
				Dict dict = Blade.create(Dict.class).findById(id);
				code = dict.getCode();
			} else {
				code = request.getParameter("blade_dict.code");
			}
			
			boolean temp = Blade.create(Dict.class).isExist("select * from blade_dict where code = #{code} and num = #{num}", CMap.init().set("code", code).set("num", num));
			
			if (temp) {
				addError(errorMessage);
			}
		} 
	}

}
