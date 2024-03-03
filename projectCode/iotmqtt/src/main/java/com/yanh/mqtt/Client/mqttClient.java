package com.yanh.mqtt.Client;

import com.yanh.mqtt.Callback.mqttCallback;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class mqttClient {
    @Value("${mqtt.url}")
    private String url;
    @Value("${mqtt.Id}")
    private String id;
    @Value("${mqtt.Topic}")
    private String topic;
    @Value("${mqtt.Qos}")
    private int qos;

    private MqttClient client;

    @PostConstruct
    public void init() {
        connect();
    }

    public void connect() {
        try {
            client = new MqttClient(url, id, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);//标识 Client 是否建立一个持久化的会话
            options.setConnectionTimeout(10);//超时
            options.setKeepAliveInterval(60);//心跳连接
            options.setAutomaticReconnect(true);//自动重连

            client.setCallback(new mqttCallback());
            client.connect();

            client.subscribe(topic,qos);

        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic, int qos) {
        try {
            client.subscribe(topic,qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public boolean isConnected() {
        return client.isConnected();
    }
    public String getClientId() {
        return client.getClientId();
    }
}
