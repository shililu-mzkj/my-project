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
package org.springblade.core.toolbox.grid;

import org.springblade.core.base.controller.BladeController;
import org.springblade.core.meta.IQuery;
import org.springblade.core.toolbox.Func;

import java.util.List;
import java.util.Map;
/**
 * GridManager
 * @author zhuangqian
 */
@SuppressWarnings("unchecked")
public class JqGridFactory extends BaseGridFactory {

	@Override
    public JqGrid<Map<String, Object>> paginate(String dbName, Integer page, Integer rows,
                                                String source, String para, String sort, String order,
                                                IQuery intercept, BladeController ctrl) {
		
		BladePage<Map<String, Object>> list = (BladePage<Map<String, Object>>) super.basePaginate(dbName, page, rows, source, para, sort, order, intercept, ctrl);

		List<Map<String, Object>> _rows = list.getRows();

        //用于oracle下jqgrid大小写敏感的场景
		if (Func.isOracle()) {
		    for (Map<String, Object> map : _rows) {
		        for (String key : map.keySet()) {
		            map.put(key.toLowerCase(), map.get(key));
                }
            }
        }

		long _total = list.getTotal();
		long _page = list.getPage();
		long _records = list.getRecords();
		
		JqGrid<Map<String, Object>> grid = new JqGrid<>(_rows, _total, _page, _records);
		return grid;
		
	}

}
