package com.tomorrow.helloworld.service.impl;

import com.tomorrow.helloworld.constants.WeChatContant;
import com.tomorrow.helloworld.service.WeChatService;
import com.tomorrow.helloworld.utils.WeChatUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author :wangshoufang
 * @date :2020/7/29 11:25
 * @description:
 */
@Service
public class WeChatServiceImpl implements WeChatService {
    @Override
    public String processMsg(HttpServletRequest request) {
        System.out.println("processMsg");
        // xml格式的消息数据
        String respXml = "success";
        // 默认返回的文本消息内容
        String respContent;
        try {
            // 调用parseXml方法解析请求消息
            Map<String,String> requestMap = WeChatUtil.parseXml(request);
            // 消息类型
            String msgType = (String) requestMap.get(WeChatContant.MsgType);
            String mes = null;
            // 文本消息
            if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_TEXT)) {
                mes =requestMap.get(WeChatContant.Content).toString();
                if("手机号".equals(mes)){
                    respContent = getSubscribeMessage(requestMap);
                    respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
                }else if("提货".equals(mes)){
                    respContent = tihuo();
                    respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
                }else if (StringUtils.isNotEmpty(mes)){
                    respContent = "别问，问就杨超越";
                    respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
                }
            }
            // 图片消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 语音消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 视频消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 地理位置消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 链接消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 事件推送
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = (String) requestMap.get(WeChatContant.Event);
                // 关注
                if (eventType.equals(WeChatContant.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                    respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
                }
                // 取消关注
                else if (eventType.equals(WeChatContant.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(WeChatContant.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(WeChatContant.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(WeChatContant.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
            System.out.println(respXml);
            return respXml;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }



    /**
     * 用户首次关注需要让用户绑定手机号
     * 拿用户的openId查询是否有绑定过
     * 没有绑定过给绑定链接
     * */
    private String getSubscribeMessage(Map<String,String> requestMap){
        String openId=requestMap.get(WeChatContant.FromUserName);
        //todo 查询是否绑定过手机号
        //没有绑定过，给用户返回链接 让绑定 account/register
        StringBuffer subscribeReplyMessage=new StringBuffer();
        StringBuffer registerUrl=new StringBuffer();
        registerUrl.append(WeChatContant.PROTOCOL).append(WeChatContant.DOMAIN).append("/account/register/").append(openId);
        subscribeReplyMessage.append("您好，您尚未绑定手机号，为了更好的为您服务，请<a href='").append(registerUrl.toString()).append("'>完善手机号</a>");
        return subscribeReplyMessage.toString();
    }


    /**
     * 去提货
     *
     * */
    private String tihuo(){
        StringBuffer subscribeReplyMessage=new StringBuffer();
        subscribeReplyMessage.append("<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx703fd1d181f0c463&redirect_uri=http://943jg5.natappfree.cc/pickup/index&response_type=code&scope=snsapi_base&state=register&connect_redirect=1#wechat_redirect'>去提货</a>");
        return subscribeReplyMessage.toString();
    }



}
