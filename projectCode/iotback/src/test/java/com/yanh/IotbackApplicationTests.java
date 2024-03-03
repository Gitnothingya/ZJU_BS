package com.yanh;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class IotbackApplicationTests {

    @Test
    void contextLoads() {
        Timestamp timestamp = new Timestamp(1703995350303L);
        LocalDateTime timestampToDate = timestamp.toLocalDateTime();
//                new LocalDateTime(timestamp.getTime());
        System.out.println("timestampToDate = " + timestampToDate);
    }

}
