package com.smallchill.test.beetlsql;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.PostgresStyle;
import org.junit.Test;

import com.smallchill.core.beetl.ReportInterceptor;
import com.smallchill.core.toolbox.Paras;
import com.smallchill.core.toolbox.kit.CharsetKit;

@SuppressWarnings("rawtypes")
public class BeetlTest {

	@Test
	public void test() {

		List<Map> list = getSqlManager().execute("select * from tb_yw_tzgg where f_vc_bt = #f_vc_bt#", Map.class, Paras.create().set("f_vc_bt", '1'), 1, 10);
		System.out.println(list);

	}

	public SQLManager getSqlManager() {
		PostgresStyle style = new PostgresStyle();
		PostgreSqlConnection cs = new PostgreSqlConnection();
		SQLLoader loader = new ClasspathLoader("/beetlsql");
		SQLManager sql = new SQLManager(style, loader, cs, new DefaultNameConversion(), new Interceptor[] { new ReportInterceptor() });
		sql.getSqlLoader().setCharset(CharsetKit.UTF_8);
		sql.getSqlLoader().setAutoCheck(true);
		return sql;
	}

}
