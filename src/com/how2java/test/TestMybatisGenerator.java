package com.how2java.test;

import com.how2java.dao.Category;
import com.how2java.dao.CategoryExample;
import com.how2java.dao.mapper.CategoryMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestMybatisGenerator {

    public static void main(String[] args) throws Exception {

//        生成mapper.java和mapper的xml文件
//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        InputStream is = TestMybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").openStream();
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(is);
//        is.close();
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
//        System.out.println("生成代码成功，刷新项目，查看文件,然后执行TestMybatis.java");

        System.out.println("先运行TestMybatisGenerator创建mapper,pojo,xml 等文件，然后取消import里被注释的，以及接下来的注释，并执行代码");

//
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();

        CategoryExample example = new CategoryExample();
        example.createCriteria().andNameLike("%9%");
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
        List<Category> cs= mapper.selectByExample(example);

        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}