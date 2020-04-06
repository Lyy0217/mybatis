package com.how2java.test;

import com.how2java.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDynamicSql {

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        //1、根据配置文件mybatis-config.xml得到sqlSessionFactory 。
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、然后再根据sqlSessionFactory 得到session
        SqlSession session = sqlSessionFactory.openSession();

//        System.out.println("查询所有");
//        List<Product> allProducts = session.selectList("DynamicSqlListProduct");
//        for (Product product : allProducts) {
//            System.out.println(product);
//        }
//
        System.out.println("模糊查询");
        Map<String, String> param = new HashMap<>();
        param.put("name", "z");
//        param.put("price","90");

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(3);
//        list.add(5);

        List<Product> likeProducts = session.selectList("bindDynamicSqlListProduct", param);

        for (Product product : likeProducts) {
            System.out.println(product);
        }

//        Product p = new Product();
//        p.setId(6);
//        p.setName("product zz");
//        p.setPrice(99.98f);
//        session.update("TrimDynamicSqlUpdateProduct", p);
//
//        listAll(session);

        session.commit();
        session.close();
    }

    private static void listAll(SqlSession session) {
        Map<String, Object> params = new HashMap<>();
//        params.put("name","a");
//        params.put("price","10");
        List<Product> ps2 = session.selectList("listProduct", params);
        for (Product p : ps2) {
            System.out.println(p);
        }
    }
}