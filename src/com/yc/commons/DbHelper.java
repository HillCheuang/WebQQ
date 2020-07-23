 package com.yc.commons;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import javax.crypto.spec.PSource;
import javax.naming.spi.DirStateFactory.Result;

import com.yc.bean.Message;

public class DbHelper {
	 //数据库连接池
    private static ConnPool  connPool = new ConnPool();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * 获取连接对象
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException{
		return connPool.getConnection();
	}
	/**
	 * 关闭所有资源
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(null !=pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 返回单条语句
	 * @param sql
	 * @param list
	 * @return
	 * @throws SQLException
	 * @throws IOException 
	 */
	public Map<String, Object> findSingle(String sql,List<Object> params) throws SQLException, IOException {
		Map<String, Object> map = null;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			setParamsList(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = getColumnNames(rs);
			//System.out.println(columnNames);
			if(rs.next()){
				//创建Map对象
				map = new HashMap<String,Object>();
				//循环所有列
				for(String name:columnNames){
					//获取值
					Object obj = rs.getObject(name);
					//判断是否为空
					if(obj == null){
						continue;
					}
					String typeName = obj.getClass().getName();
					//System.out.println(typeName);
					if("oracle.sql.BLOB".equals(typeName)){
						Blob blob = (Blob) rs.getBlob(name);
						InputStream in = blob.getBinaryStream();
						byte [] bt = new byte[(int)blob.length()];
						in.read(bt);
						//字节数组存入map中
						map.put(name, bt);
						in.close();
					}else {
						map.put(name, rs.getObject(name));
					}
				}
				
				
				
				
				
			}
		} finally {
			// TODO: handle finally clause
			closeAll(conn, pstmt, rs);
		}
		return  map;
	}
	
	public List<Map<String,Object>> findMutipl(String sql,List<Object> params) throws Exception{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParamsList(pstmt,params);
			rs=pstmt.executeQuery();
			//获取所有的列名
			List<String> columnNames=getColumnNames(rs);
			System.out.println(columnNames);
			while(rs.next()){
				//创建Map对象
				map=new HashMap<String,Object>();
				//循坏所有列
				for(String name:columnNames){
					//获取值
					Object obj=rs.getObject(name);
					//判断值是否为空
					if(null==obj){
						continue;
					}
					//获取值的类型名称
					String tyepName=obj.getClass().getName();
					if("oracle.sql.BLOB".equals(tyepName)){
						//图片
						Blob blob=(Blob) rs.getBlob(name);//BLOB处理必须在连接未关闭之前进行
						InputStream in=blob.getBinaryStream();
						byte [] bt=new byte[(int)blob.length()];
						in.read(bt);
						//字节数组存入map中
						map.put(name, bt);
						in.close();
					}else{
						map.put(name, obj);
					}
				}
				//将map添加到list集合
				list.add(map);
				rs.last();
			}
		}finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	public <T> List<T> findMutil(String sql, List<Object> params, Class<T> cls) throws Exception {
		T t = null;
		List<T> list = new ArrayList<T>();
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			setParamsList(pstmt, params);
			// 执行查询操作
			rs = pstmt.executeQuery();
			
			// 获取承有的方法
			Method[] methods = cls.getDeclaredMethods();
			
			List<String> columnNames=getColumnNames(rs);
			while (rs.next()) {
				// 创建对象
				t = cls.newInstance();
				
					// 循环method
					for (Method m : methods) {
						for(String name:columnNames) {
							if (("set" + name).equalsIgnoreCase(m.getName())) {
								// 获取该方法的形参的类垿
								String paramterType = m.getParameterTypes()[0].getName();
								if ("java.lang.Integer".equals(paramterType)) {
									
									m.invoke(t, rs.getInt(name));
								} else if ("java.lang.Double".equals(paramterType)) {
									m.invoke(t, rs.getDouble(name));
								} else if ("java.lang.Float".equals(paramterType)) {
									m.invoke(t, rs.getFloat(name));
								} else if ("java.lang.String".equalsIgnoreCase(paramterType)) {
									m.invoke(t, rs.getString(name));
								} // 如果实体类中还有其他的类型继续补充计秿
							}
						}
					
				}
				list.add(t);
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}

	public int update(List<String> sqls, List<List<Object>> params) throws SQLException{
		int reuslt = 0;
		try {
			conn = getConn();	//事务 自动提交
			//事务设置成手动提交
			conn.setAutoCommit(false);
			//循环sql语句
			if(null == sqls || sqls.isEmpty()){
				return reuslt;
			}
			for(int i = 0;i<sqls.size();i++){
				//获取sql语句
				String sql = sqls.get(i);
				//获取预编译对象
				pstmt = conn.prepareStatement(sql);
				//设置参数
				setParamsList(pstmt, params.get(i));    	//获取第i个小list设置参数
				reuslt = pstmt.executeUpdate();
				if(reuslt<=0){	//此sql语句为执行陈宫
					conn.rollback();	//设置事务回滚
					return reuslt;
				}
			}
			//事务提交
			conn.commit();
			
		}catch(SQLException e){
			//当发生异常后，也需要对其进行回滚操作
			conn.rollback();
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
			closeAll(conn, pstmt, rs);
		}
		
		
		return reuslt;
	}
	
	public List<String> getColumnNames(ResultSet rs) throws SQLException{
		List<String> list = new ArrayList<String>();
		//获取ResultSetMetaData对象
		ResultSetMetaData data = rs.getMetaData();	//获取此ResultSet 对象的列的编号、类型和数学
		int count = data.getColumnCount();
		for(int i = 1 ;i<=count;i++){
			String columnName = data.getColumnName(i);
			list.add(columnName);
		}
		
		return list;
	}
	public void setParamsList(PreparedStatement pstmt,List<Object> list) throws SQLException{
		if(null == list || list.isEmpty()){
			return;
		}
		for(int i = 0;i<list.size();i++){
			pstmt.setObject(i+1, list.get(i));
		}
	}
	/**
	 * 单条更新语句 增删改操作
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql,Object...params) {
		int result = 0;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			setParamsObject(pstmt, params);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		
		return result;
	}
	//设置参数
	private void setParamsObject(PreparedStatement pstmt, Object[] params) throws SQLException {
		// TODO Auto-generated method stub
		// 循环数组
		if(null == params || params.length<=0){
			return;
		}
		for(int i =0;i<params.length;i++){
			pstmt.setObject(i+1,params[i]);
		}
		return;
	}
	public int update(String sql,int num){
		int result = 0;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 聚合函数sum count avg mix max
	 * select count(*) from tb_user where ....
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public double getPolymer(String sql,List<Object> params) throws SQLException{
		double result = 0;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			setParamsList(pstmt, params);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
	
	
	
	
	
	public <T> T find(String sql, List<Object> params, Class<T> cls) throws Exception {
		T t = null;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			setParamsList(pstmt, params);
			rs = pstmt.executeQuery();
			
			Method[] methods = cls.getDeclaredMethods();
			//结果集列名
			List<String> columnsNames=getColumnNames(rs);
			
			if (rs.next()) {
				t = cls.newInstance();
				// 循环字段吿
			
					for (Method m : methods) {
						for(String name:columnsNames) {
							if (("set" + name).equalsIgnoreCase(m.getName())) {
								String paramterType = m.getParameterTypes()[0].getName();
								if("java.lang.Integer".equals(paramterType)) {
									m.invoke(t,rs.getInt(name));
								}else if ("java.lang.Float".equals(paramterType)) {
									m.invoke(t, rs.getFloat(name));
								} else if ("java.lang.String".equals(paramterType)) {
									m.invoke(t, rs.getString(name));
								} else if ("java.lang.Double".equals(paramterType)) {
									m.invoke(t, rs.getDouble(name));
								} else {
									
								}
								
							}
						}
					}
				}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return t;

	}
	
	
	
	
	
	
}
