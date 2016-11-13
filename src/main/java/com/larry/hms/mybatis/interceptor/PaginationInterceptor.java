package com.larry.hms.mybatis.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class PaginationInterceptor implements Interceptor{
	// select���������ʽƥ�䣺 ^����ͷλ�� \s����ո� $�����βλ�� *���������� .���������ַ�
	private static final String REGEX = "^\\s*[Ss][Ee][Ll][Ee][Cc][Tt].*$";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// ��ʱ��targetΪRoutingStatementHandler���ʵ��
		StatementHandler target = (StatementHandler)invocation.getTarget();
		// BoundSql������һ��sql���ԣ���Ϊ��ִ�е�sql���
		BoundSql boundSql = target.getBoundSql();
		String sql = boundSql.getSql().replaceAll("\r|\n","");
		// ���sql�����select���Ļ�������в鿴�Ƿ���Ҫ��ҳ����
		if(sql.matches(REGEX)) {
			// delegate��RoutingStatementHandlerͨ��mapperӳ���ļ������õ�statementType��ָ�������StatementHandler
			Object delegate = this.readField(target, "delegate");
			// rowBounds�а��������Զ���ķ�ҳ��Ϣ��������ʼλ��offset��ȡ����¼����limit
			RowBounds rowBounds = (RowBounds)this.readField(delegate, "rowBounds");
			// ���rowBound��Ϊ�գ���rowBounds����ʼλ�ò�Ϊ0�������������Ҫ���з�ҳ����
			if(rowBounds != null && rowBounds.getOffset() != RowBounds.NO_ROW_OFFSET){
				// assemSql(...)��ɶ�sql����װ�估rowBounds�����ò���
				writeField(boundSql, "sql", assemSql(sql, rowBounds));
			}
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// ͨ��Plugin��wrap(...)������ʵ�ִ���������ɲ���
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
	}
	/**
	 * ���÷����ȡָ�������ָ������
	 * @param target
	 * @param fieldName
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private Object readField(Object target, String fieldName) throws Exception{
		Field field = null;
		for(Class<?> c = target.getClass(); c!=null; c = c.getSuperclass()){
			try {
				field = c.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				//û�ҵ������ԣ���������Ҹ�������ԣ����Բ�������쳣
			} 
		}
		if(field != null){
			field.setAccessible(true);
			return field.get(target);
		}
		return null;
	}
	/**
	 * װ��SQL��䣬������RowBounds�е�offset��limit
	 * @param oldSql
	 * @param rowBounds
	 * @return
	 */
	public String assemSql(String oldSql, RowBounds rowBounds) throws Exception{
		String sql = oldSql + " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();
		// �������Ǳ���ģ���Ϊ��ǰ���û���sql����Ժ�ʵ�ʵĽ��������������Ҫ������offset��limit��������Ϊ��ʼֵ
		writeField(rowBounds, "offset", RowBounds.NO_ROW_OFFSET);
		writeField(rowBounds, "limit", RowBounds.NO_ROW_LIMIT);
		return sql;
	}
	/**
	 * ���÷���Ϊָ�������ָ������д��ֵ
	 * @param target
	 * @param fieldName
	 * @param value
	 * @throws Exception
	 */
	private void writeField(Object target, String fieldName, Object value) throws Exception{
		Field field = null;
		// ����target�����Լ��丸�������
		for (Class<?> c = target.getClass(); c != null; c = c.getSuperclass()) {
			try {
				field = c.getDeclaredField(fieldName);
			} catch (NoSuchFieldException ex) {
                // û�ҵ������ԣ���̳в��Ҹ�������ԣ����Բ�������쳣
			}
		}
		if(field != null){
			field.setAccessible(true);
			field.set(target, value);
		}
	}

}
