package com.yanh.mapper;


import com.yanh.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into users(username, password, create_time, update_time) "+
            "values(#{username},#{password}, now(), now())")
    void addUser(String username, String password);
    @Select("select * from users where username = #{username}")
    User findByUsername(String username);
    @Update("update users set nickname = #{nickname}, email = #{email}, description = #{description}, update_time = now() where username = #{username}")
    void update(User user);

    @Update("update users set password = #{newPwd}, update_time = now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}
