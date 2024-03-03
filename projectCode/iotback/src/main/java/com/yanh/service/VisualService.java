package com.yanh.service;

import com.yanh.pojo.Message;

import java.util.List;

public interface VisualService {
    Integer getAllNum();

    Integer getWarnNum();

    Integer getNormNum();

    List<Message> getLatestPoint();
}
