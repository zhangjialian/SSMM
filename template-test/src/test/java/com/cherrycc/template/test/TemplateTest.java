package com.cherrycc.template.test;

import com.cherrycc.template.common.exception.ErrorCodeException;
import com.cherrycc.template.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author BG349176
 * @date 2018/8/24 14:39
 */
public abstract class TemplateTest<T> {

    private static final Logger logger = LoggerFactory.getLogger(TemplateTest.class);

    abstract void check();

    abstract T action();

    BaseResponse<T> execute(){
        BaseResponse<T> response = new BaseResponse<>();
        try{
            this.check();
            T result = this.action();
            response.setSuccess(true);
            response.setResult(result);
        } catch (ErrorCodeException e){
            logger.info("error@execute, e={}", e);
            response.setSuccess(false);
            response.setFailedMsg(e.getErrorEnum().getDesc());
        } catch (Exception e){
            logger.info("error@execute, e={}", e);
            response.setSuccess(false);
            response.setFailedMsg(e.getMessage());
        }
        return response;
    }

}
