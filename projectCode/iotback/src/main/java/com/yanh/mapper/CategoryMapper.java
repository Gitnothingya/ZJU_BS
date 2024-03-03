package com.yanh.mapper;

import com.yanh.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into categories(name, description, create_time, update_time) " +
            "values (#{name}, #{description}, now(), now())")
    void add(Category category);

    @Select("select * from categories")
    List<Category> getAll();

    @Select("select * from categories where id = #{id}")
    Category findById(Integer id);

    @Update("update categories set name=#{name}, description=#{description},update_time=now() where id=#{id}")
    void update(Category category);

    @Delete("delete from categories where id = #{id}")
    void delete(Integer id);
}
