package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Order;
import com.how2java.pojo.OrderItem;
import com.how2java.pojo.Product;
import com.how2java.pojo.mapper.CategoryMapper;
import com.how2java.pojo.mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMapper {
    public static void main(String[] args) {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = null;
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

            //根据session获取mapper
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
//            ProductMapper mapper = session.getMapper(ProductMapper.class);
//            List<Product> productList = mapper.ManyByOneListProduct();
//
//            for (Product p : productList) {
//                System.out.println(p + "\t对应的分类是:\t" + p.getCategory().getName());
//            }

            //        add(mapper);
//        delete(mapper);
//        get(mapper);
//        update(mapper);
//            OneByManyListAll(mapper);


//            listOrder(session);
            listAll(mapper);

            session.commit();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void listOrder(SqlSession session) {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Order> os = mapper.list();
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois = o.getOrderItems();
            if (null != ois) {
                for (OrderItem oi : ois) {
                    System.out.format("\t%s\t%f\t%s%n", oi.getProduct().getName(), oi.getProduct().getPrice(), oi.getNumber());
                }
            }

        }
    }

    private static Category get(CategoryMapper mapper) {

        Category category = mapper.get(1);
        return category;
    }

    private static void update(CategoryMapper mapper) {
        Category c = mapper.get(2);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }

    private static void delete(CategoryMapper mapper) {
        mapper.delete(2);
        listAll(mapper);
    }

    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }


    private static void listAll(CategoryMapper mapper) {
        List<Category> categoryList = mapper.list();

        for (Category category : categoryList) {

            System.out.println(category);

        }
    }

    private static void OneByManyListAll(CategoryMapper mapper) {
        List<Category> cs = mapper.OneByManyList();
        for (Category c : cs) {
            System.out.println(c.getName());
            List<Product> ps = c.getProductList();
            for (Product p : ps) {
                System.out.println("\t" + p.getName());
            }
        }
    }
}