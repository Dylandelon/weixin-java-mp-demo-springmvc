package com.srpgroup.iot.wx.bean;

import me.chanjar.weixin.mp.bean.device.AbstractDeviceBean;

/**
 * @author Fightfa
 */
public class WxIotMsg extends WxBaseMsg {
    /**
     * device_id : device_id
     * device_type : device_ type
     * msg_id : 1234567890123456
     * user : user
     * msg_type : get
     * data : xxx
     * services : {"operation_status":{"status":0}}
     */
    private String device_id;
    private String device_type;
    private long msg_id;
    private String user;
    private String data;
    //TODO **需完善，http://iot.weixin.qq.com/wiki/doc/intro/%E4%BA%A7%E5%93%81%E8%83%BD%E5%8A%9B%E5%AE%9A%E4%B9%89%E6%8C%87%E5%BC%95%20V1.2.pdf
    private ServicesBean services;

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

    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ServicesBean getServices() {
        return services;
    }

    public void setServices(ServicesBean services) {
        this.services = services;
    }

    public static class ServicesBean {
        /**
         * operation_status : {"status":0}
         */

        private OperationStatusBean operation_status;

        public OperationStatusBean getOperation_status() {
            return operation_status;
        }

        public void setOperation_status(OperationStatusBean operation_status) {
            this.operation_status = operation_status;
        }

        public static class OperationStatusBean {
            /**
             * status : 0
             */

            private int status;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
