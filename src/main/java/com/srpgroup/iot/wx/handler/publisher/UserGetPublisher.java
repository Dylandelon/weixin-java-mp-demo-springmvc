package com.srpgroup.iot.wx.handler.publisher;

import com.srpgroup.iot.mqtt.config.Topic;
import com.srpgroup.iot.service.Wx2MqttService;
import com.srpgroup.iot.wx.bean.WxIotMsg;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fightfa
 */
@Component
public class UserGetPublisher extends Publisher<WxIotMsg> {

    public UserGetPublisher(Wx2MqttService wx2MqttService) {
        super(wx2MqttService);
    }

    @Override
    public boolean handle(String json, WxIotMsg wxIotMsg) {
        if (wxIotMsg == null || wxIotMsg.getDevice_id() == null) return false;
        String topic = Topic.SERVER_GET_PREFIX;
        topic += wxIotMsg.getDevice_id();
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(json.getBytes());
        mqttMessage.setQos(1);
        mqttMessage.setRetained(false);
        wx2MqttService.publish(topic, mqttMessage);
        return true;
    }
}
