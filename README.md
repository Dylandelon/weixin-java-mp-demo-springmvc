
# 《基于JAVA智能家居的微信公众号》之设备云端微信公众号服务项目(学校SRP项目)
微信硬件云+MQTT EMQ+SpringMVC+ESP8266 WIFI
2017.12
### 本项目fork自weixin-java-tools的Demo演示程序，更多Demo请查阅：https://github.com/wechat-group/weixin-java-tools
### 使用Spring MVC 框架实现微信公众号对接微信硬件平台开发功能，和对接MQTT代理服务器
### 欢迎帮忙维护添加新功能，或提供更好的实现。
###修改内容：
主要增加了com.srpgroup包，修改了原demo包中spring.service.WexinService.java，在pom.xml中增加mqtt库等。
## 具体说明见 简书上 ![https://www.jianshu.com/p/f8892a8b3fc7](https://www.jianshu.com/p/f8892a8b3fc7)
## 使用步骤：
1. 配置: 复制/src/main/resources/wx.properties.template 生成 wx.properties 文件，填写相关配置;
1. 配置: 复制/src/main/resources/mqtt.properties.template 生成 mqtt.properties 文件，填写相关配置;		
1. 使用maven运行demo程序: `mvn jetty:run`  或者自己打war包发布到tomcat运行；
1. 配置微信公众号中的接口地址：http://xxx/wechat/portal （注意XXX需要是外网可访问的域名，需要符合微信官方的要求）；
1. 根据自己需要修改各个handler的实现，加入自己的业务逻辑。

	
