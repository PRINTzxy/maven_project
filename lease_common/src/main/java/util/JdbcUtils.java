package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//数据库连接的工具类
public class JdbcUtils {

    /*
    *   ThreadLocal  是一个容器:  保存数据的,根据当前线程共享数据
    *  当前的例子:保存connection ,实现了本线程不变的情况下数据共享
    * */
     private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();


    //数据源(连接池)
     private static DataSource dataSource;
     static {
         try {
             //读取数据库连接信息
             InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
             Properties properties=new Properties();
             properties.load(resourceAsStream);
             //创建连接池
             dataSource = DruidDataSourceFactory.createDataSource(properties);
         } catch (Exception e) {
            e.printStackTrace();
         }
     }

     //从连接池获取连接
    public static Connection getConnection(){
        try {
            //先从本地线程容器中获取
            Connection connection = threadLocal.get();
            if(connection==null){
                //从数据源获取有效的连接
               connection=  dataSource.getConnection();
               //把connection 保存到本地线程容器
                threadLocal.set(connection);
            }

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //从连接池获取连接
    public static void closeConnection(){
        try {
            Connection connection = getConnection();
            if(connection!=null){
                 connection.close(); // 把连接对象归还给连接池
                threadLocal.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public static void removeConnection(){
            Connection connection = threadLocal.get();
            if(connection!=null){
                threadLocal.remove();
            }

    }


    //开启事务的方法
    public static void beginTransaction(){
         //获取连接对象
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    //提交事务的方法
    public static void commitTransaction(){
        //获取连接对象
        try {
            Connection connection = getConnection();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //回滚事务的方法
    public static void rollbackTransaction(){
        //获取连接对象
        try {
            Connection connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
