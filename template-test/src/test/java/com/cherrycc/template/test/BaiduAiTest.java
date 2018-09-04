package com.cherrycc.template.test;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author BG349176
 * @date 2018/9/3 14:15
 */
public class BaiduAiTest {

    //设置APPID/AK/SK
    public static final String APP_ID = "11763773";
    public static final String API_KEY = "dmkL7mXQpnTgWDtXAAvPDajl";
    public static final String SECRET_KEY = "Aue8ZbALqmTdU2SXTIgLTLEB5HG2kfwD";

    /**
     * 文字识别
     */
    @Test
    public void test1() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\BG349176\\Desktop\\verify-code1.jpeg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }

    /**
     * 汽车识别
     */
    @Test
    public void test2() {
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        options.put("baike_num", "5");

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String image = "C:\\Users\\BG349176\\Desktop\\detech-cat.jpg";
        JSONObject res = client.carDetect(image, options);
        System.out.println(res.toString(2));

    }

}
