package com.yanh.service;

import com.yanh.pojo.Device;
import com.yanh.pojo.PageBean;

public interface DeviceService {
    void addDevice(Device device);

    void deleteDevice(Integer id);

    Device getDetail(Integer id);

    void updateDevice(Device device);

    PageBean<Device> list(Integer pageNum, Integer pageSize, String name, Integer categoryId, Integer state);

    Integer getWarnNum(Integer categoryId);

    Integer getNormNum(Integer categoryId);

    Integer getOfflineNum(Integer categoryId);
}
