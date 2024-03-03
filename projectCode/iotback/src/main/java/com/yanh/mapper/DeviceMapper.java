package com.yanh.mapper;

import com.yanh.pojo.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeviceMapper {

    @Insert("insert into devices(name, category_id, description, state, create_time, update_time)" +
            "values (#{name}, #{categoryId}, #{description},#{state}, now(), now())")
    void add(Device device);

    @Delete("delete from devices where id = #{id}")
    void delete(Integer id);

    @Select("select * from devices where id = #{id}")
    Device get(Integer id);

    @Update("update devices set name = #{name}, category_id = #{categoryId}, description = #{description}, state = #{state} where id = #{id}")
    void update(Device device);

    Integer getWarn(Integer categoryId);

    Integer getNorm(Integer categoryId);

    Integer getOffline(Integer categoryId);


    List<Device> list(String name, Integer categoryId, Integer state);


}
