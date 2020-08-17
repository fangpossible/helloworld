package com.tomorrow.helloworld.domain;

import lombok.Data;

/**
 * @author :wangshoufang
 * @date :2020/8/14 10:54
 * @description:
 */
@Data
public class VerificationCodeRequest {

    /**
     * 公众号下唯一标识 防刷
     */
    private String openId;


    /**
     * 手机号
     */
    private String phone;
}
