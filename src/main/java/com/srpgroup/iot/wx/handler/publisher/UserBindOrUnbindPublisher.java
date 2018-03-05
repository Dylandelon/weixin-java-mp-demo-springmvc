package com.srpgroup.iot.wx.handler.publisher;

import com.srpgroup.iot.mqtt.config.Topic;
import com.srpgroup.iot.service.Wx2MqttService;
import com.srpgroup.iot.wx.bean.WxIotBindUnbindMsg;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fightfa
 */
@Component
public class UserBindOrUnbindPublisher extends Publisher<WxIotBindUnbindMsg> {
    public UserBindOrUnbindPublisher(Wx2MqttService wx2MqttService) {
        super(wx2MqttService);
    }

    @Override
    public boolean handle(String json, WxIotBindUnbindMsg wxIotBindUnbindMsg) {
        /*接收微信硬件平台的请求：[signature=[20be183f09b9be7d13a8da9dc1467dbf17eaa677], encType=[null], msgSignature=[null], timestamp=[1514104429], nonce=[166684536], requestBody=[
{"device_id":"gh_51c420a3a430_d4f4bda4cbdcd9f2","device_type":"gh_51c420a3a430","msg_id":455315288,"msg_type":"bind","create_time":1514104429,"open_id":"oTiHkjla0lR-VZ4i0OxnQCUNTMH8","session_id":0,"content":"","qrcode_suffix_data":""}
]*/
        //收到user要绑定的命令,则转为topic:
        //server/bind/{device_id}
        //设备异步收到后，储存好，返回device/bind_ack,内容ok or err，，并订阅新主题 user/{openid}/command，

        if (wxIotBindUnbindMsg == null || wxIotBindUnbindMsg.getDevice_id() == null) return false;
        String topic = Topic.SERVER_BIND_PREFIX;
        if (WxIotBindUnbindMsg.MSG_TYPE_UNBIND.equals(wxIotBindUnbindMsg.getMsg_type()))
            topic = Topic.SERVER_UNBIND_PREFIX;
        topic += wxIotBindUnbindMsg.getDevice_id();
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(json.getBytes());
        mqttMessage.setQos(2);
        mqttMessage.setRetained(true);
        wx2MqttService.publish(topic, mqttMessage);
        return true;
    }
}
