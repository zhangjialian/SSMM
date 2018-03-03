package com.zjl.controller;

import com.zjl.common.user.UserBO;
import com.zjl.common.exception.ErrorCodeException;
import com.zjl.common.response.SuccessResponse;
import com.zjl.common.systemEnum.ErrorCodeEnum;
import com.zjl.common.systemEnum.PageURLEnum;
import com.zjl.common.systemEnum.SessionEnum;
import com.zjl.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhangjialian on 2017/11/9.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Resource
    private UserManager userManager;
    /*@Resource
    private LoginManager loginManager;*/

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 打开用户登录页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap map){
        return "login/index";
    }

    /**
     * 用户登录
     * @param map
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submitLogin", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public SuccessResponse<Object> submitLogin(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        session.setAttribute(SessionEnum.LOGIN_USER.getCode(), null);

        int userId = 1;
        UserBO user = userManager.getUserById(userId);

        if(user == null){
            throw new ErrorCodeException(ErrorCodeEnum.LG02);
        }
        session.setAttribute(SessionEnum.LOGIN_USER.getCode(), user);

        //如果存在要跳转的url，则直接跳转
        String requestUrl = (String) session.getAttribute(SessionEnum.REQUEST_UTL.getCode());
        if(!StringUtils.isEmpty(requestUrl)){
            response.sendRedirect(requestUrl);
        }else{
            response.sendRedirect(PageURLEnum.ADMIN_INDEX.getUrl());
        }

        return getSuccessResponse("登录成功");
    }

    /**
     * 用户登出
     * @param map
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public SuccessResponse<Object> logout(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        session.setAttribute(SessionEnum.LOGIN_USER.getCode(), null);
        response.sendRedirect(PageURLEnum.LOGIN.getUrl());
        return getSuccessResponse("退出成功");
    }





}
