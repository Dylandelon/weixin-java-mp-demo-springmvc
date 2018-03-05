package com.srpgroup.iot.wx.bean;

import me.chanjar.weixin.mp.bean.device.AbstractDeviceBean;

/**
 * @author Fightfa
 */
public class ToWxIotMsg extends AbstractDeviceBean {
    /**
     * device_id : device_id
     * device_type : device_ type
     * msg_id : 1234567890123456
     * user : user
     * msg_type : get
     * data : xxx
     * services : {"operation_status":{"status":0}}
     */
    public static final String MSG_TYPE_NOTIFY="notify";

    private String device_id;
    private String device_type;
    private String msg_type;
    private String data;
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

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
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
