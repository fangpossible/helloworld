package com.tomorrow.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :wangshoufang
 * @date :2020/8/13 17:36
 * @description:
 * 提货码
 */
@Controller
@RequestMapping("/pickup")
public class PickUpController {


    @RequestMapping("/index")
    public String index() {
        return  "pickup/index";
    }
}
