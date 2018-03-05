package com.srpgroup.iot.wx.handler.subscriber;

import com.github.binarywang.demo.spring.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.device.WxDeviceBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceAckUnbindSubscriber extends Subcriber<WxDeviceBind>{

    public DeviceAckUnbindSubscriber(WeixinService wxService) {
        super(wxService);
    }

    @Override
    public void handle(String deviceId, WxDeviceBind wxDeviceBind) throws WxErrorException {
        //TODO 状态更新进数据库
//        wxService.getDeviceService().compelUnbind(wxDeviceBind);
    }
}
