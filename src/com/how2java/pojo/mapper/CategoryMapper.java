package com.how2java.pojo.mapper;

import com.how2java.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 新增加接口CategoryMapper ，并在接口中声明的方法上，加上注解
 * 对比配置文件Category.xml，其实就是把SQL语句从XML挪到了注解上来
 */
public interface CategoryMapper {

    @Insert(" insert into category_ (name) values (#{name}) ")
    public int add(Category category);

    @Delete("delete from category_ where id = #{id}")
    public void delete(int id);

    @Update("update category_ set name = #{name} where id = #{id}")
    public int update(Category category);

    @Select("select * from category_ where id = #{id}")
    public Category get(int id);

    @Select(" select * from category_ ")
    public List<Category> list();

    /**
     * 一对多查询
     *
     * @return
     * @Select 注解获取Category类本身
     * @Results 通过@Result和@Many中调用ProductMapper.listByCategory()方法相结合，来获取一对多关系
     */
    @Select("select * from category_")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productList", javaType = List.class, column = "id", many = @Many(
                    select = "com.how2java.pojo.mapper.ProductMapper.listByCategory"
            ))
    })
    public List<Category> OneByManyList();

    //    分页
    @Select(" select * from category_")
    public List<Category> listByPage();


//    public List<Category> listByPage(@Param("start") int start, @Param("cnt") int cnt);


    @Select(" select count(*) from category_ ")
    public int count();
}