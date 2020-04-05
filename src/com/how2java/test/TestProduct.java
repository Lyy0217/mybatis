package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestProduct {

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        //1、根据配置文件mybatis-config.xml得到sqlSessionFactory 。
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、然后再根据sqlSessionFactory 得到session
        SqlSession session = sqlSessionFactory.openSession();


        //3、最后通过session的selectList方法，调用sql语句listCategory。listCategory这个就是在配置文件Category.xml中那条sql语句设置的id。
        //执行完毕之后，得到一个Category集合，遍历即可看到数据。
        List<Product> products = session.selectList("ManyByOneListProduct");

//        for (Product product : products) {
//            System.out.println("name:" + product.getName() + " " + "prcie:" + product.getPrice());
//        }

        for (Product p : products) {
            System.out.println(p + " 对应的分类是 \t " + p.getCategory());
        }

    }
}