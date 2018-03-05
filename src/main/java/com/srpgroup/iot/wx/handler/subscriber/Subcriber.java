package com.srpgroup.iot.wx.handler.subscriber;

import com.github.binarywang.demo.spring.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;

/**
 * @author Fightfa
 */
public abstract class Subcriber<Msg> {
    @SuppressWarnings("unchecked")
    protected WeixinService wxService;

    public Subcriber(WeixinService wxService) {
        this.wxService = wxService;
    }

    @SuppressWarnings("unchecked")
    private Class<Msg> getMsgClass() {
        return (Class<Msg>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void handle(String deviceId, MqttMessage mqttMessage) throws WxErrorException {
        ResolvableType resolvableType1 = ResolvableType.forClass(Subcriber.class);
        Msg msg = WxGsonBuilder.create().fromJson(new String(mqttMessage.getPayload()), getMsgClass());
        handle(deviceId, msg);
    }

    public abstract void handle(String deviceId, Msg msg) throws WxErrorException;
}
