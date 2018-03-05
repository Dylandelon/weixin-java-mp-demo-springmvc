package com.srpgroup.iot.wx.controller;

import com.github.binarywang.demo.spring.service.WeixinService;
import com.srpgroup.iot.service.Wx2MqttService;
import com.srpgroup.iot.wx.bean.BothSyncResponse;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fightfa
 */
@RestController
@RequestMapping("/wxiot/portal")
public class WxIotPortalController {
    @Autowired
    private Wx2MqttService wx2MqttService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.wx2MqttService.getWxService().checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @ResponseBody
    @PostMapping(produces = "application/json; charset=UTF-8")
    public String handlePost(@RequestBody String requestBody,
                             @RequestParam("signature") String signature,
                             @RequestParam(name = "encrypt_type", required = false) String encType,
                             @RequestParam(name = "msg_signature", required = false) String msgSignature,
                             @RequestParam("timestamp") String timestamp,
                             @RequestParam("nonce") String nonce) {
        this.logger.info(
                "\n【接收到微信硬件平台的请求】：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!this.wx2MqttService.getWxService().checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        BothSyncResponse response;
//TODO **分发器
        if ("aes".equals(encType)) {
            //TODO
            // aes加密的消息
            WxMpCryptUtil cryptUtil = new WxMpCryptUtil(this.wx2MqttService.getWxService().getWxMpConfigStorage());
            requestBody = cryptUtil.decrypt(msgSignature, timestamp, nonce, requestBody);
            this.logger.debug("\n消息解密后内容为：\n{} ", requestBody);
        }
        // 明文传输的消息
        response=wx2MqttService.route(requestBody);

        String out = response.toJson();
        this.logger.debug("\n组装回复微信硬件平台的信息：{}", out);
        return out;
    }

}
