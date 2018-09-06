package com.cherrycc.template.service.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * kafka消费者
 * @author BG349176
 * @date 2018/9/6 17:43
 */
public class KafkaConsumerListener implements MessageListener<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    /**
     * 监听器自动执行该方法
     *     消费消息
     *     自动提交offset
     *     执行业务代码
     *     （high level api 不提供offset管理，不能指定offset进行消费）
     * @param record
     */
    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        logger.info("=============kafkaConsumer开始消费=============");
        String topic = record.topic();
        String key = record.key();
        String value = record.value();
        long offset = record.offset();
        int partition = record.partition();
        logger.info("-------------topic:"+topic);
        logger.info("-------------value:"+value);
        logger.info("-------------key:"+key);
        logger.info("-------------offset:"+offset);
        logger.info("-------------partition:"+partition);
        logger.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }
}
