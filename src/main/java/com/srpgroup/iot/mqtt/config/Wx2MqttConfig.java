package com.srpgroup.iot.mqtt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fightfa
 */
@Configuration
public class Wx2MqttConfig {
    @Value("#{mqttProperties.host}")
    private String host;

    @Value("#{mqttProperties.port}")
    private String port;

    @Value("#{mqttProperties.wx2mqtt_userName}")
    private String userName;

    @Value("#{mqttProperties.wx2mqtt_userName}")
    private String clientId;

    @Value("#{mqttProperties.wx2mqtt_passWord}")
    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getClientId() {
        return this.clientId;
    }

}

