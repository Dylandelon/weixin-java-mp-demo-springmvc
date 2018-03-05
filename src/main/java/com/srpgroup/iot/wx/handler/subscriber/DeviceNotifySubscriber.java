package com.srpgroup.iot.wx.handler.subscriber;

import com.github.binarywang.demo.spring.service.WeixinService;
import com.srpgroup.iot.wx.bean.ToWxIotMsg;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Component
public class DeviceNotifySubscriber extends Subcriber<ToWxIotMsg> {

    public DeviceNotifySubscriber(WeixinService wxService) {
        super(wxService);
    }

    @Override
    public void handle(String deviceId, MqttMessage mqttMessage) throws WxErrorException {
//        super.handle(deviceId, mqttMessage);

        wxService.getWxIotDeviceService().notifyMsg(new String(mqttMessage.getPayload()));
    }

    @Override
    public void handle(String deviceId, ToWxIotMsg toWxIotMsg) throws WxErrorException {
//     {"msg_type":"notify","services":{"operation_status":{"status":1},"power_switch":{"on_off":true},"lightbulb":{"alpha":5}}}
        toWxIotMsg.setDevice_id(deviceId);
        wxService.getWxIotDeviceService().notifyMsg(toWxIotMsg);
    }

}
