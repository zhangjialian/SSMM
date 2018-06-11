package com.zjl.controller;

import com.zjl.manager.TestManager;
import com.zjl.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestManager testManager;

    @RequestMapping(value = "/showInfo", method = RequestMethod.GET)
    @ResponseBody
    public String showInfo(){
        return "123213";
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public String test1(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Cookie[] cookies = getCookies(request);
        for(Cookie cookie : cookies){
            System.out.println(cookie.getName() + '-' + cookie.getValue());
        }
        return "123123123";
    }



    @RequestMapping(value = "/testTransaction", method = RequestMethod.GET)
    @ResponseBody
    public String testTransaction() throws Exception{
        testManager.testTransaction();
        System.out.println(123123);
        return "123123";
    }

    @RequestMapping(value = "/insertTest", method = RequestMethod.GET)
    @ResponseBody
    public String insertTest() throws Exception{
        testManager.insertTest();
        return "23123123";
    }

}
