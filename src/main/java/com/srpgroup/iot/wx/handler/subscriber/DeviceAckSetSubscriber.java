package com.srpgroup.iot.wx.handler.subscriber;

import com.github.binarywang.demo.spring.service.WeixinService;
import com.srpgroup.iot.wx.bean.ToWxIotMsgAsyResponse;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.device.WxDeviceBind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceAckSetSubscriber extends Subcriber<ToWxIotMsgAsyResponse>{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DeviceAckSetSubscriber(WeixinService wxService) {
        super(wxService);
    }

    @Override
    public void handle(String deviceId, ToWxIotMsgAsyResponse toWxIotMsgAsyResponse) throws WxErrorException {
        logger.info("toWxIotMsgAsyResponse:{}",toWxIotMsgAsyResponse);
        wxService.getWxIotDeviceService().asyResponseSet(toWxIotMsgAsyResponse);
    }
}
