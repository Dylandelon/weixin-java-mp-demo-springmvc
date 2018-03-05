package com.srpgroup.iot.mqtt.config;

/**
 * @author Fightfa
 */
public class Topic {
    //用来subscribe进的：
    //    public static final String DEVICE_PREFIX = "device/+/#";
    public static final String DEVICE_PREFIX = "device";
    //用来分路由处理的：
    public static final String DEVICE_ACK_BIND = "device/+/ack/bind";
    public static final String DEVICE_ACK_UNBIND = "device/+/ack/unbind";
    public static final String DEVICE_ACK_GET = "device/+/ack/get";
    public static final String DEVICE_ACK_SET = "device/+/ack/set";
    public static final String DEVICE_NOTIFY = "device/+/notify";
    public static final int[] QOS = {2, 2, 2,2,2};
    public static final String[] TOPICS = {
            DEVICE_ACK_BIND,
            DEVICE_ACK_UNBIND,
            DEVICE_ACK_GET,
            DEVICE_ACK_SET,
            DEVICE_NOTIFY};


    //用来publish出的
//    public static final String USER_BIND= "user/{username}/bind/{device_id}";
    public static final String SERVER_BIND_PREFIX = "server/bind/";
    public static final String SERVER_UNBIND_PREFIX = "server/unbind/";
    public static final String SERVER_SET_PREFIX = "server/set/";
    public static final String SERVER_GET_PREFIX = "server/get/";
}
