package com.cherrycc.template.test;

import com.cherrycc.template.service.mq.KafkaProducerServer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author BG349176
 * @date 2018/9/6 18:07
 */
public class KafkaTest extends BaseTest {

    @Autowired
    private KafkaProducerServer kafkaProducer;

    @Test
    public void test(){
        String topic = "orderTopic";
        String value = "test";
        String ifPartition = "0";
        Integer partitionNum = 3;
        String role = "test";//用来生成key
        Map<String,Object> res = kafkaProducer.sndMesForTemplate
                (topic, value, ifPartition, partitionNum, role);

        System.out.println("测试结果如下：===============");
        String message = (String)res.get("message");
        String code = (String)res.get("code");

        System.out.println("code:"+code);
        System.out.println("message:"+message);
    }

}
