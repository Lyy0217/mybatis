package com.how2java.test;

import com.how2java.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis的缓存都在session上，只要通过session查过的数据库数据，都会放在session上，
 * 下一次再查询相同id的数据，都会直接从缓存中查出来，为不会再查询数据库了
 */
public class TestCache {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session1 = sqlSessionFactory.openSession();

        Category c1 = session1.selectOne("selectCategory", 8);
        System.out.println(c1);
        Category c2 = session1.selectOne("selectCategory", 8);
        System.out.println(c2);

        session1.commit();
        session1.close();


        /**
         * 1、一级缓存在session上，另开一个session的话，会重新查数据
         */
        SqlSession session2 = sqlSessionFactory.openSession();
        Category c3 = session2.selectOne("selectCategory", 8);
        System.out.println(c3);
        session2.commit();
        session2.close();


    }
}