package com.starfish.test;

import com.starfish.common.exception.ErrorCodeException;
import com.starfish.common.query.UserQuery;
import com.starfish.common.systemEnum.ErrorCodeEnum;
import org.junit.Test;

/**
 * @author BG349176
 * @date 2018/8/24 14:57
 */
public class Mytest0824 {

    @Test
    public void test1(){
        UserQuery query = new UserQuery();

        new TemplateTest<String>() {
            @Override
            void check() {
                if(query == null){
                    throw new ErrorCodeException(ErrorCodeEnum.P01);
                }
            }

            @Override
            String action() {
                return null;
            }
        }.execute();
    }

}
