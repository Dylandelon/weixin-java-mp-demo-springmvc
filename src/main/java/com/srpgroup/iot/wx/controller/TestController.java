package com.srpgroup.iot.wx.controller;

import com.github.binarywang.demo.spring.service.WeixinService;
import com.google.gson.Gson;
import com.srpgroup.iot.service.Wx2MqttService;
import com.srpgroup.iot.wx.bean.TestBody;
import com.srpgroup.iot.wx.handler.publisher.UserBindOrUnbindPublisher;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.device.WxDeviceBind;
import me.chanjar.weixin.mp.bean.device.WxDeviceBindDeviceResult;
import me.chanjar.weixin.mp.bean.device.WxDeviceBindResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fightfa
 */
@RestController
@RequestMapping("/test")
public class TestController {
    public  static void main(String arg[]){
        String s="device/+";
        String sp[]=s.split("/",3);
        System.out.println("\nl="+sp.length);
        for(String a:sp){
            System.out.println("\na="+a);
        }
    }

    @Autowired
    private WeixinService wxService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String API_URL_PREFIX = "https://api.weixin.qq.com/hardware/mydevice/platform/sendmsgtodevicecloud";

    @PostMapping("/sendmsgtodevicecloud")
    @ResponseBody
    public String sendmsgtodevicecloud(@RequestBody String requestBody) throws WxErrorException {
        String url = API_URL_PREFIX;
        String response = this.wxService.post(url, requestBody);
        return response;
    }

    @GetMapping("ub")
    @ResponseBody
    public String testUb() {
        WxDeviceBind wxDeviceBind=new WxDeviceBind();
        wxDeviceBind.setDeviceId("gh_51c420a3a430_d4f4bda4cbdcd9f2");
        wxDeviceBind.setOpenId("oTiHkjla0lR-VZ4i0OxnQCUNTMH8");
        try {
            WxDeviceBindResult wxDeviceBindResult = wxService.getDeviceService().compelUnbind(wxDeviceBind);
            return wxDeviceBindResult.toString();
        } catch (WxErrorException e) {
            e.printStackTrace();
            return e.toString();
        }
//        return "Hello!GET /test/ub";
    }
    @Autowired
    Wx2MqttService wx2MqttService;
    @GetMapping("pub")
    @ResponseBody
    public String testPub() {
//        wx2MqttService.init();
//        new UserBindOrUnbindPublisher().handle("{\"device_id\":\"gh_51c420a3a430_d4f4bda4cbdcd9f2\",\"device_type\":\"gh_51c420a3a430\",\"msg_id\":455315288,\"msg_type\":\"bind\",\"create_time\":1514104429,\"open_id\":\"oTiHkjla0lR-VZ4i0OxnQCUNTMH8\",\"session_id\":0,\"content\":\"\",\"qrcode_suffix_data\":\"\"}");
        return "Hello!GET /test/pub";
    }
    @GetMapping("g")
    @ResponseBody
    public String testG() {
        WxDeviceBind wxDeviceBind=new WxDeviceBind();
        wxDeviceBind.setDeviceId("gh_51c420a3a430_d4f4bda4cbdcd9f2");
        wxDeviceBind.setOpenId("oTiHkjla0lR-VZ4i0OxnQCUNTMH8");
        try {
            WxDeviceBindDeviceResult wxDeviceBindDeviceResult = wxService.getDeviceService().getBindDevice(wxDeviceBind.getOpenId());
            return wxDeviceBindDeviceResult.toString();
        } catch (WxErrorException e) {
            e.printStackTrace();
            return e.toString();
        }
//        return "Hello!GET /test/ub";
    }
    @GetMapping
    @ResponseBody
    public String index() {
        return "Hello!GET /test123456";
    }

    @PostMapping
    @ResponseBody
    public String index(@RequestBody String s) {
//        TestBody testBody = new Gson().fromJson(s, TestBody.class);
        TestBody testBody = WxGsonBuilder.create().fromJson(s, TestBody.class);
//        return WxGsonBuilder.create().toJson(testBody);
        return  new Gson().toJson(testBody);
    }

    //    @PostMapping(value = "/testBody")
//    @PostMapping(value = "/testBody", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public String testBody(@RequestBody String s) {
//        return s;
//    }
//    @PostMapping(value = "/testBody", produces = "application/json; charset=UTF-8")
    @PostMapping(value = "/testBody")
    @ResponseBody
    public String testBody(@RequestBody TestBody requestBody) {
        return requestBody.toString();
//        return request/.get+requestBody.toString();
    }


}
