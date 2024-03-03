package com.yanh.mqtt.Controller;


import com.yanh.mqtt.Client.mqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class bootController {
    private mqttClient myClient;

    @RequestMapping("/connect")
    @ResponseBody
    public String connect() {
        String Result;
        if(myClient.isConnected()) {
            Result =  "mqtt客户端 "+myClient.getClientId()+" 已连接。";
        }
        else {
            Result = "mqtt客户端 "+myClient.getClientId()+" 未连接。";
        }
        return Result;
    }

}
