package com.yanh.service.impl;

import com.yanh.mapper.MessageMapper;
import com.yanh.pojo.Message;
import com.yanh.service.VisualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisualServiceImpl implements VisualService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public Integer getAllNum() {
        return messageMapper.allNum();
    }

    @Override
    public Integer getWarnNum() {
        return messageMapper.warnNum();
    }

    @Override
    public Integer getNormNum() {
        return messageMapper.normNum();
    }

    @Override
    public List<Message> getLatestPoint() {
        return messageMapper.getLatestPoints();
    }
}
