package com.cherrycc.template.monitor.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author BG349176
 * @date 2018/8/15 10:33
 */
@Aspect
@Component
public class UpdateOptimisticLockAspect {


    /**
     * 乐观锁更新判断更新条数是否大于0，如果不是 抛异常
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.cherrycc.template.dao..*(..)) && @annotation(com.cherrycc.template.common.annotation.UpdateOptimisticLock)")
    public Object beforeLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
        // TODO: 2018/8/20 配在接口方法上不生效，待优化
        /*System.out.println("===begin to check optimistic lock update");

        int result = (int) joinPoint.proceed();
        if(result < 1){
            throw new ErrorCodeException(ErrorCodeEnum.DB01);
        }
        System.out.println("===check optimistic lock update finished");
        return result;*/
    }
}
