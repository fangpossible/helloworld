package com.tomorrow.helloworld.controller;

import com.tomorrow.helloworld.service.WeChatService;
import com.tomorrow.helloworld.utils.WeChatUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author :wangshoufang
 * @date :2020/7/29 10:19
 * @description:
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Resource
    private WeChatService weChatService;
    /**
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
     *
     * signature 微信端发来的签名,signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机数
     * echostr   微信端发来的验证字符串
     *
     * 加密/校验流程如下
     * 1.将token、timestamp、nonce三个参数进行字典序排序
     * 2.将三个参数字符串拼接成一个字符串进行sha1加密
     * 3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @GetMapping(value = "message")
    public String access(@RequestParam(value = "signature") String signature,
                           @RequestParam(value = "timestamp") String timestamp,
                           @RequestParam(value = "nonce") String nonce,
                           @RequestParam(value = "echostr") String echostr) {
        return WeChatUtil.checkSignature(signature, timestamp, nonce) ? echostr : null;
    }


    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(value = "message")
    public String processMsg(HttpServletRequest request) {
        // 调用核心服务类接收处理请求
        return weChatService.processMsg(request);
    }
}
