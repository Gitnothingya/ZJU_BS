package com.yanh.mapper;

import com.yanh.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("select count(*) from messages")
    Integer allNum();
    @Select("select count(*) from messages where alert = 1")
    Integer warnNum();
    @Select("select count(*) from messages where alert = 0")
    Integer normNum();

    @Select("select m.* from messages m inner join " +
            "(select client_id, max(timestamp) as max_timestamp from messages group by client_id)" +
            "latest_msg on  m.client_id = latest_msg.client_id and m.timestamp = latest_msg.max_timestamp")
    List<Message> getLatestPoints();
}
