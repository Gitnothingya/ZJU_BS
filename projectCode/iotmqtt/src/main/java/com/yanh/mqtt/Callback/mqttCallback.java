package com.yanh.mqtt.Callback;

import com.alibaba.fastjson2.JSON;
import com.yanh.mqtt.mapper.msgMapper;
import com.yanh.mqtt.pojo.Message;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;


@Component
public class mqttCallback implements MqttCallback {

    @Resource
    private msgMapper msgM;
    public static mqttCallback mqttCb;

    @PostConstruct
    public void init() {
        mqttCb = this;
        mqttCb.msgM = this.msgM;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("与服务器断开连接，请尝试重连！！！");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        try{
            String payload = new String(mqttMessage.getPayload());
//            System.out.println(payload);
            Message msg = JSON.parseObject(payload,Message.class);

            mqttCb.msgM.add(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("信息传递。");
    }
}
