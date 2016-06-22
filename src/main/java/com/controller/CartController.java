package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yzy on 2016/3/4.
 */
@RequestMapping("/cart")
@Controller
public class CartController {

    @RequestMapping("getInfo.do")
    @ResponseBody
    public String getInfo(){
        System.out.println("hhhhhh");
        return "china";
    }


}
