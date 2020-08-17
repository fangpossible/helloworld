package com.tomorrow.helloworld.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tomorrow.helloworld.domain.VerificationCodeRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :wangshoufang
 * @date :2020/8/13 10:46
 * @description:
 * 账号相关
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    /**
     * @param openId
     * @return
     */
    @RequestMapping("/register/{openId}")
    public String register(@PathVariable("openId") String openId) {
        System.out.println(openId);
        return  "account/register";
    }


    /**
     * 发送验证码
     * @param verificationCodeRequest
     * @return
     */
    @PostMapping("/verificationcode")
    public String sendVerificationCode(@RequestBody  VerificationCodeRequest verificationCodeRequest) {
        System.out.println();
        return  "account/register";
    }
}
