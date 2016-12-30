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
package com.smallchill.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smallchill.core.base.controller.CurdController;
import com.smallchill.core.interfaces.IMeta;
import com.smallchill.system.meta.factory.LLogFactory;
import com.smallchill.system.model.LoginLog;

@Controller
@RequestMapping("/llog")
public class LLogController extends CurdController<LoginLog>{

	@Override
	protected Class<? extends IMeta> metaFactoryClass() {
		
		return LLogFactory.class;
	}

}
