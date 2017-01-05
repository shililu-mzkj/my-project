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
package com.smallchill.core.plugins.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smallchill.core.plugins.connection.ConnectionPlugin;
import com.smallchill.core.toolbox.redis.Cache;

public class RedisManager {
	private static Map<String, Cache> pool = new ConcurrentHashMap<String, Cache>();
	
	public static Cache init() {
		return init(ConnectionPlugin.init().MASTER);
	}

	public static Cache init(String name) {
		Cache rc = pool.get(name);
		if (null == rc) {
			synchronized (RedisManager.class) {
				rc = pool.get(name);
				if (null == rc) {
					rc = ConnectionPlugin.init().getRedisCachePool().get(name);
					pool.put(name, rc);
				}
			}
		}
		return rc;
	}

	private RedisManager() {}

}
