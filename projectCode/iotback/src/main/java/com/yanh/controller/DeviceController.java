package com.yanh.controller;


import com.yanh.pojo.Device;
import com.yanh.pojo.PageBean;
import com.yanh.pojo.Result;
import com.yanh.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public Result addDevice(@RequestBody @Validated(Device.Add.class) Device device) {
        System.out.println(device.toString());
        deviceService.addDevice(device);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteDevice(Integer id) {
        deviceService.deleteDevice(id);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result getDetail(Integer id){
        Device device = deviceService.getDetail(id);
        return Result.success(device);
    }

    @PutMapping
    public Result updateDevice(@RequestBody @Validated(Device.Update.class) Device device) {
        deviceService.updateDevice(device);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Device>> listDevice(
                Integer pageNum,
                Integer pageSize,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) Integer categoryId,
                @RequestParam(required = false) Integer state
    ) {
        PageBean<Device> pb = deviceService.list(pageNum,pageSize,name,categoryId,state);
        return Result.success(pb);
    }

    @GetMapping("/warn")
    public Result getWarnDevice(Integer id){
        Integer num =  deviceService.getWarnNum(id);
        return Result.success(num);
    }

    @GetMapping("/norm")
    public Result getNormDevice(Integer id){
        Integer num =  deviceService.getNormNum(id);
        return Result.success(num);
    }

    @GetMapping("/offline")
    public Result getOfflineDevice(Integer id){
        Integer num =  deviceService.getOfflineNum(id);
        return Result.success(num);
    }



}
