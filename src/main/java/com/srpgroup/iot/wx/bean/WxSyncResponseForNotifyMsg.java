package com.srpgroup.iot.wx.bean;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.device.TransMsgResp;

/**
 * @author Fightfa
 */
public class WxSyncResponseForNotifyMsg extends BothSyncResponse {
    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }

    private long msg_id;
    public static WxSyncResponseForNotifyMsg fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxSyncResponseForNotifyMsg.class);
    }

}
