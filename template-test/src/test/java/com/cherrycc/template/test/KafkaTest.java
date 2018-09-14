package com.cherrycc.template.test;

import com.alibaba.fastjson.JSON;
import com.cherrycc.template.model.UserDO;
import com.cherrycc.template.service.mq.KafkaProducerServer;
import com.cherrycc.template.utils.MockTestUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Properties;

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
        Integer partitionNum = 1;
        String key = "test:10001";
        String value = JSON.toJSONString(MockTestUtils.getJavaBean(UserDO.class));
        Map<String,Object> res = kafkaProducer.sndMesForTemplate(topic, partitionNum, key, value);

        System.out.println("测试结果如下：===============");
        String message = (String)res.get("message");
        String code = (String)res.get("code");

        System.out.println("code:"+code);
        System.out.println("message:"+message);
    }

    //@Test
    public void test5(){
        //消息发送模式：同步或异步
        boolean isAsync = true;

        Properties properties = new Properties();
        //Kafka服务端的主机名和端口号
        properties.put("bootstrap.servers", "47.105.44.101:9092");
        //客户的ID
        properties.put("client.id", "ProducerDemo");
        //消息的key和value都是字节数组，为了将Java对象转化为字节数组，可以配置
        //key.serializer和value.serializer两个序列化器，完成转化
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        // StringSerializer用来将String对象序列化成字节数组
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //生产者核心类
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);
        String topic = "test";
        //消息的Key
        int messageKey = 1;
        while (true) {
            // 消息的value
            String messageValue = "Message_" + messageKey;
            long startTime = System.currentTimeMillis();
            if (isAsync) {
                //异步发送消息
                // 第一个参数是ProducerRecord类型的对象，封装了目标Topic、消息的key、消息的value
                // 第二个参数是一个CallBack对象，当生产者接收到Kafka发来的ACK确
                // 认消息的时候，会调用此CallBack对象的onCompletion()方法，实现 回调功能
                ProducerRecord<Integer, String> record = new ProducerRecord<>(topic, messageKey, messageValue);
                producer.send(record, new DemoCallBack<>(startTime, messageKey, messageValue));
            } else {
                //同步发送消息
                //KafkaProducer.send()方法的返回值类型是Future<RecordMetadata>
                //这里通过Future.get()方法，阻塞当前线程，等待Kafka服务端的ACK响应
                ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>(topic, messageKey, messageValue);
                try {
                    RecordMetadata recordMetadata = producer.send(producerRecord).get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            messageKey++;
        }
    }

}

class DemoCallBack<K, V> implements Callback {
    private final long startTime;
    private final K key;
    private final V value;

    public DemoCallBack(long startTime, K key, V value) {
        this.startTime = startTime;
        this.key = key;
        this.value = value;
    }

    /**
     * 生产者成功发送消息，收到Kafka服务端发来的ACK确认消息后，会调用此回调函数
     *
     * @param recordMetadata 生产者发送的消息的元数据，如果发送过程中出现异常，此参数为null
     * @param exception      发送过程中出现的异常，如果发送成功，则此参数为null
     */
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
        if (recordMetadata != null) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("message(" + key + "," + value + ") send to partition("
                    + recordMetadata.partition() + ")," + "offset(" + recordMetadata.offset() + ") in" + elapsedTime);
        } else {
            exception.printStackTrace();
        }
    }
}
