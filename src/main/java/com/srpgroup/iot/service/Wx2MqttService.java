package com.srpgroup.iot.service;

import com.github.binarywang.demo.spring.service.WeixinService;
import com.srpgroup.iot.mqtt.config.Topic;
import com.srpgroup.iot.mqtt.config.Wx2MqttConfig;
import com.srpgroup.iot.wx.bean.BothSyncResponse;
import com.srpgroup.iot.wx.bean.WxBaseMsg;
import com.srpgroup.iot.wx.handler.publisher.UserBindOrUnbindPublisher;
import com.srpgroup.iot.wx.handler.publisher.UserGetPublisher;
import com.srpgroup.iot.wx.handler.publisher.UserSetPublisher;
import com.srpgroup.iot.wx.handler.subscriber.*;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * @author Fightfa
 */
//TODO 此服务写个外部手动start stop restart status命令
//TODO --另加一个接收h5的service
@Service
public class Wx2MqttService implements MqttCallback {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private MqttClient client;
    @Autowired
    private WeixinService wxService;

    @Autowired
    private Wx2MqttConfig wx2MqttConfig;

    @PostConstruct
    public void init() {
        logger.info("初始化Wx2MqttService");
        //TODO 分出支持tls格式的config。
        try {
            client = new MqttClient(wx2MqttConfig.getHost() + ":" + wx2MqttConfig.getPort(), wx2MqttConfig.getClientId(), new MemoryPersistence());
            reConnect();
        } catch (MqttException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }

    }

    private void subscribe() throws MqttException {
        //订阅消息
        logger.info("初始订阅消息");
        client.subscribe(Topic.TOPICS, Topic.QOS);
    }

    public void reConnect() {
        try {
            connect();
            subscribe();
            client.setCallback(this);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private MqttConnectOptions connectOptions = null;

    private MqttConnectOptions getConnectOptions() {
        if (connectOptions == null) {
            connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(false);
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setUserName(wx2MqttConfig.getUserName());
            connectOptions.setPassword(wx2MqttConfig.getPassWord().toCharArray());
            // 设置超时时间 TODO 这两个参数设入properties中
            connectOptions.setConnectionTimeout(10);
            // 设置会话心跳时间
            connectOptions.setKeepAliveInterval(20);
        }
        return connectOptions;
    }

    private void connect() throws MqttException {
        //阻塞在此直到连上或超时没连上
        logger.info("阻塞在此直到连上或超时没连上");
        client.connect(getConnectOptions());
        logger.info("阻塞完毕:Connected=" + client.isConnected());
    }

    // 连接丢失后，一般在这里面进行重连
    @Override
    public void connectionLost(Throwable throwable) {
        //TODO - 多次重连不行时发短信等方式通知维护人员（标-的TODO当前并不优先考虑实现）
//        logger.info("连接断开");
//        logger.info("连接断开，正在重连");
//        reConnect();
    }

    // subscribe后得到的消息会执行到这里面
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
//TODO ** topic分发器,类似微信公众号mp-demo中的xml message router（标*的TODO比较重要）
        logger.info("\n【接收到mqtt消息】：[topic=[{}],qos=[{}] mqttMessage=[{}]", topic,mqttMessage.getQos(), mqttMessage);
        if (StringUtils.isEmpty(topic)) {
            return;
        }
        //将类似device/+/ack/bind按/分为3组，split[0]为device，1为device_id，2为后缀
        String[] split = topic.split("/", 3);
        if (Topic.DEVICE_PREFIX.equals(split[0])) {
            if (split.length == 3) {
                String deviceId=split[1];
                String suffix=split[2];
                //DEVICE_NOTIFY消息比较多，放前一点来判断：
                if (Topic.DEVICE_NOTIFY.endsWith(suffix)) {
                    logger.info("\n【解析mqtt消息】：类型:{}，"+Topic.DEVICE_ACK_SET,suffix);
                    new DeviceNotifySubscriber(wxService).handle(deviceId, mqttMessage);
                }else if (Topic.DEVICE_ACK_SET.endsWith(suffix)) {
                    logger.info("\n【解析mqtt消息】：类型:{}，"+Topic.DEVICE_ACK_SET,suffix);
                    new DeviceAckSetSubscriber(wxService).handle(deviceId, mqttMessage);
                }else if (Topic.DEVICE_ACK_GET.endsWith(suffix)) {
                    new DeviceAckGetSubscriber(wxService).handle(deviceId, mqttMessage);
                }else if (Topic.DEVICE_ACK_BIND.endsWith(suffix)) {
                    new DeviceAckBindSubscriber(wxService).handle(deviceId, mqttMessage);
                } else if (Topic.DEVICE_ACK_UNBIND.endsWith(suffix)) {
                    new DeviceAckUnbindSubscriber(wxService).handle(deviceId, mqttMessage);
                }
                //扩展
            }
        }
    }

    // publish后会执行到这里
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("publish后的deliveryComplete：" + iMqttDeliveryToken.isComplete() + "");
    }
//{"asy_error_code":0,"asy_error_msg":"ok","msg_id":471106528,"msg_type":"set","device_id":"gh_51c420a3a430_d4f4bda4cbdcd9f2","device_type":"gh_51c420a3a430","services":{"operation_status":{"status":0},"power_switch":{"on_off":true},"lightbulb":{"alpha":5}}}
//{"asy_error_code":0,"asy_error_msg":"ok","msg_id":471106528,"msg_type":"set","device_id":"gh_51c420a3a430_d4f4bda4cbdcd9f2","device_type":"gh_51c420a3a430","services":{"operation_status":{"status":0},"power_switch":{"on_off":true},"lightbulb":{"alpha":5}}}??
//{"asy_error_code":0,"asy_error_msg":"ok","msg_id":471106528,"msg_type":"set","device_id":"gh_51c420a3a430_d4f4bda4cbdcd9f2","device_type":"gh_51c420a3a430","services":{"operation_status":{"status":0},"power_switch":{"on_off":true},"lightbulb":{"alpha":5}}}???
    public BothSyncResponse route(String jsonRequestBody) {
        WxBaseMsg wxBaseMsg = WxBaseMsg.fromJson(jsonRequestBody);
        if (WxBaseMsg.MSG_TYPE_BIND.equals(wxBaseMsg.getMsg_type()) || WxBaseMsg.MSG_TYPE_UNBIND.equals(wxBaseMsg.getMsg_type())) {
            new UserBindOrUnbindPublisher(this).handle(jsonRequestBody);
        } else if (WxBaseMsg.MSG_TYPE_SET.equals(wxBaseMsg.getMsg_type())) {
            new UserSetPublisher(this).handle(jsonRequestBody);
        } else if (WxBaseMsg.MSG_TYPE_GET.equals(wxBaseMsg.getMsg_type())) {
            new UserGetPublisher(this).handle(jsonRequestBody);
        }
        return BothSyncResponse.OK();
    }

    public boolean publish(String topicStr, MqttMessage mqttMessage) {
        logger.info("准备publish的topic:" + topicStr);
        logger.info("准备publish的mqttMsg:" + mqttMessage);
        if (client == null || !client.isConnected()) {
            reConnect();
            logger.error("Wx2MqttService未连接上，无法publish");
            return false;
        }
        try {
            MqttTopic topic = client.getTopic(topicStr);
            MqttDeliveryToken token = topic.publish(mqttMessage);
            token.waitForCompletion();
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public WeixinService getWxService() {
        return wxService;
    }
}
