package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * addCategory对应的插入sql语句，#{name}会自动获取category对象的name属性值
 */
public class TestAddMybatis {

    public static void main(String[] args) {

        try {
            //1、根据配置文件获取SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

//            Category category = new Category();
//            category.setName("水果");

//            session.insert("insertCategory", category);
//            category.setId(2);
//            session.delete("deleteCategory", category);
//            category.setName("newCategory");
//            Category c = session.selectOne("selectCategory", 1);
//            session.update("updateCategory", category);
//            System.out.println(c.getName());

//            Map<String, Object> param = new HashMap<>();
//            param.put("id", 1);
//            param.put("name", "cate");

            //模糊查询
            List<Category> categories = session.selectList("oneByManyListCategory");

//            listAllCategory(session);

            for (Category c : categories) {
                System.out.println(c);
                List<Product> ps = c.getProductList();
                for (Product p : ps) {
                    System.out.println("\t" + p);
                }
            }

            session.commit();
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listAllCategory(SqlSession session) {
        List<Category> categoryList = session.selectList("listCategory");

        categoryList.forEach(category -> System.out.println(category.getName()));
    }
}