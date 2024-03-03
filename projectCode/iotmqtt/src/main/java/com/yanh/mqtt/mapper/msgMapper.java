package com.yanh.mqtt.mapper;


import com.yanh.mqtt.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface msgMapper {

    @Insert("insert into messages(alert, client_id, info, lat, lng, timestamp, value)" +
            "values(#{alert},#{clientId},#{info},#{lat},#{lng},#{timestamp},#{value})")
    void add(Message msg);
}
