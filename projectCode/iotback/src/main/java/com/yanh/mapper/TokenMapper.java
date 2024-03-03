package com.yanh.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TokenMapper {

    @Insert("insert into tokens(token, expire) values(#{token},date_add(now(), interval 1 hour))")
    void add(String token);

    @Delete("delete from tokens where token = #{token}")
    void delete(String token);

    @Select("select count(*) from tokens where token = #{token}")
    boolean check(String token);
}
