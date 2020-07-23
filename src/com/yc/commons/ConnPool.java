package com.yc.commons;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ConnPool {
    // 使用LinkedList集合存放数据库连接
    private static LinkedList<Connection> connPool = new LinkedList<Connection>();

    // 在静态代码块中加载配置文件
    static {
    	//如果以jar包运行，此处会报找不到这个文件的异常，解决方案如下。
        String path = ConnPool.class.getClassLoader().getResource("db.propertties").getPath();//解决方案需要注释这行代码
        //InputStream in = PropertiesUtil.class.getClassLoader.getResourceAsStream("db.properties");//开启这行代码解决以上问题

        FileInputStream in;//开启以上解决方案需要注释调这行代码
        try {
            in = new FileInputStream(path);//开启以上解决方案需要注释这行代码
            Properties prop = new Properties();
            prop.load(in);
            String driver = prop.getProperty("driverName");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            // 数据库连接池的初始化连接数的大小
            int InitSize = Integer.parseInt(prop.getProperty("InitSize"));
            // 加载驱动
            Class.forName(driver);
            for (int i = 0; i < InitSize; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                // 将创建的连接添加的list中
                connPool.add(conn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*获取数据库连接*/
    public Connection getConnection() throws SQLException {
        if(connPool.size() > 0){
            //从集合中获取一个连接
            final Connection conn = connPool.removeFirst(); 
            //返回Connection的代理对象
            return (Connection) Proxy.newProxyInstance(ConnPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args)
                        throws Throwable {
                    if(!"close".equals(method.getName())){
                        return method.invoke(conn, args);
                    }else{
                        connPool.add(conn);
                        return null;
                    }
                }
            });
        }else{
            throw new RuntimeException("数据库繁忙，稍后再试............");
        }
    }

    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    public void setLoginTimeout(int seconds) throws SQLException {

    }

    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public Object unwrap(Class iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class iface) throws SQLException {
        return false;
    }

    public Connection getConnection(String username, String password)
            throws SQLException {
        return null;
    }
}
