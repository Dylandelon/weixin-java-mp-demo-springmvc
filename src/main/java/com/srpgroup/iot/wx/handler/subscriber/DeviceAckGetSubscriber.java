package com.srpgroup.iot.wx.handler.subscriber;

import com.github.binarywang.demo.spring.service.WeixinService;
import com.srpgroup.iot.wx.bean.BothSyncResponse;
import com.srpgroup.iot.wx.bean.ToWxIotMsgAsyResponse;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceAckGetSubscriber extends Subcriber<ToWxIotMsgAsyResponse> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DeviceAckGetSubscriber(WeixinService wxService) {
        super(wxService);
    }

    @Override
    public void handle(String deviceId, ToWxIotMsgAsyResponse toWxIotMsgAsyResponse) throws WxErrorException {
        logger.info("wxService.getWxIotDeviceService().asyResponseGet:{}",toWxIotMsgAsyResponse);
        BothSyncResponse response = wxService.getWxIotDeviceService().asyResponseGet(toWxIotMsgAsyResponse);
        logger.info("response:{}",response);

    }
}
