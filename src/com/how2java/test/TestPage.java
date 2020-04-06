package com.how2java.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.pojo.Category;
import com.how2java.pojo.mapper.CategoryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPage {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        xmlWay(session);
//        annotationWay(session);

        session.commit();
        session.close();
    }

    private static void xmlWay(SqlSession session) {
        Map<String, Object> params = new HashMap<>();
//        params.put("start", 5);
//        params.put("cnt", 5);
        PageHelper.offsetPage(0, 5);
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c);
        }

        PageInfo pageInfo = new PageInfo<>(cs);
        System.out.println("总数：" + pageInfo.getTotal());
        System.out.println(pageInfo);
    }

    private static void annotationWay(SqlSession session) {
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

        PageHelper.offsetPage(5, 5);
        List<Category> cs = mapper.listByPage();
        for (Category c : cs) {
            System.out.println(c);
        }

        PageInfo pageInfo = new PageInfo<>(cs);
        System.out.println("总数：" + pageInfo.getTotal());
        System.out.println(pageInfo);
    }
}