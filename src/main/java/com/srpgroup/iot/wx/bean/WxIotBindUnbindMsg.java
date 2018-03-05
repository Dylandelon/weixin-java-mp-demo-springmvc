package com.srpgroup.iot.wx.bean;

import java.io.Serializable;

/**
 * @author Fightfa
 */
public class WxIotBindUnbindMsg extends WxBaseMsg {

    /**
     * device_id : device_id
     * device_type : device_type
     * msg_id : 123456789
     * msg_type : device_text
     * create_time : 1457337567
     * open_id : openid
     * session_id : 123456789
     * content : content
     */

    private String device_id;
    private String device_type;
    private int msg_id;
    private int create_time;
    private String open_id;
    private int session_id;
    private String content;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WxIotBindUnbindMsg{" +
                "device_id='" + device_id + '\'' +
                ", device_type='" + device_type + '\'' +
                ", msg_id=" + msg_id +
                ", msg_type='" + msg_type + '\'' +
                ", create_time=" + create_time +
                ", open_id='" + open_id + '\'' +
                ", session_id=" + session_id +
                ", content='" + content + '\'' +
                '}';
    }
}
