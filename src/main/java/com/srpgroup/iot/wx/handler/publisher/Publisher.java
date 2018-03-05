package com.srpgroup.iot.wx.handler.publisher;

import com.srpgroup.iot.service.Wx2MqttService;
import com.srpgroup.iot.wx.handler.subscriber.Subcriber;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;

/**
 * @author Fightfa
 */
public abstract class Publisher<Msg>{
    @SuppressWarnings("WeakerAccess")
    protected Wx2MqttService wx2MqttService;

    public Publisher(Wx2MqttService wx2MqttService) {
        this.wx2MqttService=wx2MqttService;
    }

    public abstract boolean handle(String json, Msg msg);
    @SuppressWarnings("unchecked")
    private Class<Msg> getMsgClass() {
        return (Class<Msg>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public final boolean handle(String json) {
        ResolvableType resolvableType = ResolvableType.forClass(Subcriber.class);
        Msg msg= WxGsonBuilder.create().fromJson(json, getMsgClass());
        return handle(json,msg);
    }
}
