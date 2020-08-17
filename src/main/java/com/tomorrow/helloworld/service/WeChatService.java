package com.tomorrow.helloworld.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :wangshoufang
 * @date :2020/7/29 11:25
 * @description:
 */
public interface WeChatService {
    String processMsg(HttpServletRequest request);
}
