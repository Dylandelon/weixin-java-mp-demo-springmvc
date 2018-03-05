package com.srpgroup.iot.wx.bean;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.device.AbstractDeviceBean;

/**
 * @author Fightfa
 */
public class BothSyncResponse extends AbstractDeviceBean {
    /**
     * error_code : 0
     * error_msg : ok
     */
    public static BothSyncResponse OK() {
        BothSyncResponse bothSyncResponse = new BothSyncResponse();
        bothSyncResponse.error_code = 0;
        bothSyncResponse.error_msg = "ok";
        return bothSyncResponse;
    }

    private int error_code;
    private String error_msg;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static BothSyncResponse fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, BothSyncResponse.class);
    }
}
