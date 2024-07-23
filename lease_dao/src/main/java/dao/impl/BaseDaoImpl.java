package dao.impl;
/*
* 所有自定义实现类的父类
* */


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public  class BaseDaoImpl {
    QueryRunner queryRunner=new QueryRunner();
    /*
    * 增删改方法的通用方法
    * */
    public int commonUpdate(String sql,Object...args){
        try {
            return queryRunner.update(JdbcUtils.getConnection(),sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            System.out.println("1111111111111111111");
             JdbcUtils.closeConnection();
        }
    }



    /*
    * 查询多行  的通用方法
    * */

    public <T> List<T> beanList(Class<T> clazz,String sql,Object...args ){

        try {
           return queryRunner.query(JdbcUtils.getConnection(), sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.closeConnection();
        }
    }



    /*
     * 查询单行 的通用方法
     * */


    public <T> T getBean(Class<T> clazz,String sql,Object...args ){

        try {
            return queryRunner.query(JdbcUtils.getConnection(), sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    /*
     * 查询聚合函数返回单列值 的通用方法
     * */

    public  Object getValue(String sql,Object...args ){

        try {
            return queryRunner.query(JdbcUtils.getConnection(), sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
