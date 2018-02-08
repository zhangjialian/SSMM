package com.zjl.controller;


import com.zjl.common.exception.ErrorCodeException;
import com.zjl.common.response.ErrorResponse;
import com.zjl.common.response.SuccessResponse;
import com.zjl.common.systemEnum.ErrorCodeEnum;
import com.zjl.common.systemEnum.SessionEnum;
import com.zjl.common.user.UserBO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zjf on 2017/11/10.
 */
public class BaseController {

    /**
     * ��ȡ��װ��ĳɹ����ؽ��
     * @param result ���ؽ��
     * @return
     */
    protected SuccessResponse<Object> getSuccessResponse(Object result) {
        SuccessResponse<Object> resp = new SuccessResponse<>();
        resp.setResult(result);
        return resp;
    }

    protected Cookie[] getCookies(HttpServletRequest request) throws ErrorCodeException{
        if (request == null){
            throw new ErrorCodeException(ErrorCodeEnum.P01);
        }
        return request.getCookies();
    }

    /**
     * ��ȡ�û���ʶ��cookie��Ϣ
     * @param request
     * @param key
     * @return
     * @throws ErrorCodeException
     */
    protected String getUserCookie(HttpServletRequest request, String key) throws ErrorCodeException {
        if(request == null || StringUtils.isEmpty(key)){
            throw new ErrorCodeException(ErrorCodeEnum.P01);
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(key.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }

    protected UserBO getUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (UserBO) session.getAttribute(SessionEnum.LOGIN_USER.getCode());
    }

    /**
     * �����쳣��Ϣ
     * @param request
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler
    @ResponseBody
    protected ErrorResponse<Object> handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
        if(e instanceof ErrorCodeException){
            ErrorCodeEnum errorCodeEnum = ((ErrorCodeException) e).getErrorEnum();
            ErrorResponse<Object> errorResponse = new ErrorResponse<Object>(errorCodeEnum);
            errorResponse.setResult(null);
            return errorResponse;
        }
        throw e;
    }
}
