package com.srpgroup.iot.wx.bean;

/**
 * @author Fightfa
 */
public class ToWxIotMsgAsyResponse extends WxIotMsg {
    private String asy_error_code;
    private String asy_error_msg;

    @Override
    public String toString() {
        return "ToWxIotMsgAsyResponse{" +
                "asy_error_code='" + asy_error_code + '\'' +
                ", asy_error_msg='" + asy_error_msg + '\'' +
                ", msg_type='" + msg_type + '\'' +
                "} " + super.toString();
    }

    public String getAsy_error_code() {
        return asy_error_code;
    }

    public void setAsy_error_code(String asy_error_code) {
        this.asy_error_code = asy_error_code;
    }

    public String getAsy_error_msg() {
        return asy_error_msg;
    }

    public void setAsy_error_msg(String asy_error_msg) {
        this.asy_error_msg = asy_error_msg;
    }

}
