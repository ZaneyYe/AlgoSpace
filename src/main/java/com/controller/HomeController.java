package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yzy on 2016/8/29.
 */
@RestController(value = "/")
public class HomeController {

    @RequestMapping("/home.do")
    public String toHome(){
        return "/home.jsp";
    }

}
