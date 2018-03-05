package com.srpgroup.iot.wx.bean;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.device.AbstractDeviceBean;

/**
 * @author Fightfa
 */
public class WxBaseMsg extends AbstractDeviceBean {

    public static final String MSG_TYPE_BIND="bind";
    public static final String MSG_TYPE_UNBIND="unbind";

    public static final String MSG_TYPE_GET="get";
    public static final String MSG_TYPE_SET="set";

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    /**
     * msg_type : get
     */
    @SuppressWarnings("WeakerAccess")
    protected String msg_type;
    public static WxBaseMsg fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WxBaseMsg.class);
    }
}
