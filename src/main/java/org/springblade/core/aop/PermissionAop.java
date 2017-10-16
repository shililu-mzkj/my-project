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
package org.springblade.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springblade.core.annotation.Permission;
import org.springblade.core.exception.NoPermissionException;
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.check.PermissionCheckManager;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AOP 权限自定义检查
 * @author zhuangqian
 */
@Aspect
@Component
public class PermissionAop {

	@Pointcut(value = "@annotation(org.springblade.core.annotation.Permission)")
	private void cutPermission() {

	}

	@Around("cutPermission()")
	public Object doPermission(ProceedingJoinPoint point) throws Throwable {
		MethodSignature ms = (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		Permission permission = method.getAnnotation(Permission.class);
		Object[] permissions = permission.value();
		boolean flag = (permissions.length == 1 && Func.toStr(permissions[0]).equals("ALL"))
                || permissions == null || permissions.length == 0;
		if (flag) {
            /**
             * 检查全体角色
             */
			boolean result = PermissionCheckManager.checkAll();
			if (result) {
				return point.proceed();
			} else {
				throw new NoPermissionException();
			}
		} else {
            /**
             * 检查指定角色
             */
			boolean result = PermissionCheckManager.check(permissions);
			if (result) {
				return point.proceed();
			} else {
				throw new NoPermissionException();
			}
		}

	}

}
