package info.yinhua.core.data.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import info.yinhua.core.web.type.PaginatedList;

/**
 * 指定select查询方法中，有参数继承自PaginatedList的，开启分布拦截。
 * 包括设定查询总件数。
 */
// http://iyiguo.net/blog/2013/05/26/mybatis-simple-pagination/
// https://blog.csdn.net/likewindy/article/details/51351221
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class PaginationInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler target = (StatementHandler) invocation.getTarget();
		BoundSql boundSql = target.getBoundSql();
		Object parameterObject = boundSql.getParameterObject();

		if (parameterObject instanceof PaginatedList) {

			StatementHandler delegate = (StatementHandler) FieldUtils.readField(target, "delegate", true);
			MappedStatement mappedStatement = (MappedStatement) FieldUtils.readField(delegate, "mappedStatement", true);
			SqlCommandType sqlCommandType = (SqlCommandType) FieldUtils.readField(mappedStatement, "sqlCommandType",
					true);

			if (SqlCommandType.SELECT == sqlCommandType) {

				@SuppressWarnings("rawtypes")
				PaginatedList paginatedList = (PaginatedList) parameterObject;
				Connection connection = (Connection) invocation.getArgs()[0];

				paginatedList.setFullListSize(count(paginatedList, mappedStatement, connection));

				String sql = boundSql.getSql();
				FieldUtils.writeField(boundSql, "sql", paginatedSql(sql, paginatedList), true);
			}
		}
		return invocation.proceed();
	}

	private int count(@SuppressWarnings("rawtypes") PaginatedList paginatedList, MappedStatement mappedStatement,
			Connection connection) throws SQLException {

		BoundSql boundSql = mappedStatement.getBoundSql(paginatedList);
		String sql = boundSql.getSql();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

		String countSql = countSql(sql);
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings,
				paginatedList);
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paginatedList, countBoundSql);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			parameterHandler.setParameters(pstmt);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return -1;
	}

	/**
	 * for h2
	 * 
	 * @param sql
	 * @param paginatedList
	 * @return
	 */
	private String paginatedSql(String sql, @SuppressWarnings("rawtypes") PaginatedList paginatedList) {
		return sql + String.format(" limit %d offset %d", paginatedList.getObjectsPerPage(), paginatedList.getOffset());
	}

	private String countSql(String sql) {
		sql = sql.replaceFirst("(?is)select.+from", "select count(*) from");
		sql = sql.replaceAll("(?is)order.+$", "");
		return sql;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties arg0) {
	}

}
