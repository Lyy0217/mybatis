package com.how2java.test;

import com.how2java.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 1. 应用程序找Mybatis要数据
 * 2. mybatis从数据库中找来数据
 *      2.1 通过mybatis-config.xml 定位哪个数据库
 *      2.2 通过Category.xml执行对应的select语句
 *      2.3 基于Category.xml把返回的数据库记录封装在Category对象中
 *      2.4 把多个Category对象装在一个Category集合中
 * 3. 返回一个Category集合
 */
public class TestMybatis {
    public static void main(String[] args) throws IOException {
        //1、根据配置文件mybatis-config.xml得到sqlSessionFactory 。
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、然后再根据sqlSessionFactory 得到session
        SqlSession session = sqlSessionFactory.openSession();


        //3、最后通过session的selectList方法，调用sql语句listCategory。listCategory这个就是在配置文件Category.xml中那条sql语句设置的id。
        //执行完毕之后，得到一个Category集合，遍历即可看到数据。
        List<Category> cs = session.selectList("listCategory");

        for (Category c : cs) {
            System.out.println(c.getName());
        }

    }
}