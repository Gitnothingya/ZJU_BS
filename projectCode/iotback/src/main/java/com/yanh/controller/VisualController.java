package com.yanh.controller;


import com.yanh.pojo.Message;
import com.yanh.pojo.Result;
import com.yanh.service.VisualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class VisualController {
    @Autowired
    VisualService visualService;

    @GetMapping("/all")
    public Result<Integer> getAllNum() {
        Integer num = visualService.getAllNum();
        return Result.success(num);
    }
    @GetMapping("/warn")
    public  Result<Integer> getWarnNum() {
        Integer num = visualService.getWarnNum();
        return Result.success(num);
    }
    @GetMapping("/norm")
    public  Result<Integer> getNormNum() {
        Integer num = visualService.getNormNum();
        return Result.success(num);
    }

    @GetMapping("/points")
    public Result getNewMsg() {
        List<Message> lm= visualService.getLatestPoint();
        return Result.success(lm);
    }
}
