package com.srpgroup.iot.service;

import com.srpgroup.iot.wx.bean.BothSyncResponse;
import com.srpgroup.iot.wx.bean.ToWxIotMsg;
import com.srpgroup.iot.wx.bean.ToWxIotMsgAsyResponse;
import com.srpgroup.iot.wx.bean.WxSyncResponseForNotifyMsg;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpDeviceService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.device.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxIotDeviceService {
    private static final String IOT_API_URL_PREFIX = "https://api.weixin.qq.com/hardware/mydevice/platform";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WxMpService wxMpService;

    public WxIotDeviceService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    public WxSyncResponseForNotifyMsg notifyMsg(String msgJson) throws WxErrorException {
        String url = IOT_API_URL_PREFIX + "/notify?";
        logger.info("【测试notifyMsg json:】:{}",msgJson);
        String response = wxMpService.post(url, msgJson);
        return WxSyncResponseForNotifyMsg.fromJson(response);
    }
    public WxSyncResponseForNotifyMsg notifyMsg(ToWxIotMsg msg) throws WxErrorException {
        String url = IOT_API_URL_PREFIX + "/notify?";
        String response = wxMpService.post(url, msg.toJson());
        return WxSyncResponseForNotifyMsg.fromJson(response);
    }
    public BothSyncResponse asyResponseSet(ToWxIotMsgAsyResponse msg) throws WxErrorException {
//        String url = IOT_API_URL_PREFIX + "/set_resp?";
        String url = IOT_API_URL_PREFIX + "/set?";
        String response = wxMpService.post(url, msg.toJson());
        return BothSyncResponse.fromJson(response);
    }
    public BothSyncResponse asyResponseGet(ToWxIotMsgAsyResponse msg) throws WxErrorException {
        String url = IOT_API_URL_PREFIX + "/get?";
//        String url = IOT_API_URL_PREFIX + "/get_resp";
        String response = wxMpService.post(url, msg.toJson());
        return BothSyncResponse.fromJson(response);
    }

}