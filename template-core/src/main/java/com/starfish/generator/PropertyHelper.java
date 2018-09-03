package com.starfish.generator;

import org.apache.commons.beanutils.PropertyUtils;

public class PropertyHelper {

    /**
     * 必须是public类
     *
     * @param obj 必须是public类
     * @param key 字段名称
     * @return
     */
    public static Object getValue(Object obj, String key) {

        try {
            return PropertyUtils.getNestedProperty(obj, key);
        } catch (Exception e) {
            return null;
        }
    }

    public static void setValue(Object obj, String key, Object value) {

        try {
            PropertyUtils.setProperty(obj, key, value);
        } catch (Exception e) {
            // logger.error(LogMessageUtil.printStack(e));
        }

    }

    public static void setNestedPropertyValue(Object obj, String key, Object value) {

        try {
            PropertyUtils.setNestedProperty(obj, key, value);
        } catch (Exception e) {
            // logger.error(LogMessageUtil.printStack(e));
        }

    }
}
