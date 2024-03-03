package com.yanh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yanh.mapper.DeviceMapper;
import com.yanh.pojo.Device;
import com.yanh.pojo.PageBean;
import com.yanh.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void addDevice(Device device) {
        deviceMapper.add(device);
    }

    @Override
    public void deleteDevice(Integer id) {
        deviceMapper.delete(id);
    }

    @Override
    public Device getDetail(Integer id) {
        return deviceMapper.get(id);
    }

    @Override
    public void updateDevice(Device device) {
        deviceMapper.update(device);
    }

    @Override
    public PageBean<Device> list(Integer pageNum, Integer pageSize, String name, Integer categoryId, Integer state) {
        //创建pb对象
        PageBean<Device>  pb = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        //Mapper获取数据
        List<Device> ld = deviceMapper.list(name, categoryId, state);
        //转换
        Page<Device> pd = (Page<Device>) ld;

        //设置返回数据
        pb.setTotal(pd.getTotal());
        pb.setItems(pd.getResult());

        return pb;
    }

    @Override
    public Integer getWarnNum(Integer categoryId) {
        Integer num =  deviceMapper.getWarn(categoryId);
        return num;
    }

    @Override
    public Integer getNormNum(Integer categoryId) {
        Integer num =  deviceMapper.getNorm(categoryId);
        return num;
    }

    @Override
    public Integer getOfflineNum(Integer categoryId) {
        Integer num =  deviceMapper.getOffline(categoryId);
        return num;
    }
}
