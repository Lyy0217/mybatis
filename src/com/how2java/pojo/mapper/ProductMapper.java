package com.how2java.pojo.mapper;

import com.how2java.pojo.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {

    @Select(" select * from product_ where cid = #{cid}")
    public List<Product> listByCategory(int cid);

    @Select("select * from product_")
    @Results(
            @Result(property = "category", column = "cid", one = @One(select = "com.how2java.pojo.mapper.CategoryMapper.get"))
    )
    public List<Product> ManyByOneListProduct();

    @Select("select * from product_ where id = #{id}")
    public Product get(int id);
}