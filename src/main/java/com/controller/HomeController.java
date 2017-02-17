package com.controller;

import com.service.demo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yzy on 2016/8/29.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Resource
    private Student student;

    @RequestMapping("/home.do")
    public String toHome(HttpServletRequest request){
        request.setAttribute("yzy",student);
        return "/home";
    }

}
