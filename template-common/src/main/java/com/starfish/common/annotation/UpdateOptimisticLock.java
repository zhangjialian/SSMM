package com.starfish.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 乐观锁校验是否修改成功
 *      使用本注解的方法必须为update操作，且需要判断对应的lock_version
 * @author BG349176
 * @date 2018/8/15 10:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface UpdateOptimisticLock {

}
