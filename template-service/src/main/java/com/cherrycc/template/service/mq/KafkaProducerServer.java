package com.cherrycc.template.service.mq;

import com.cherrycc.template.common.mq.KafkaMesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author BG349176
 * @date 2018/9/6 18:11
 */
@Component
public class KafkaProducerServer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * kafka发送消息模板
     * @param topic 主题
     * @param partitionNum 分区数 如果是否使用分区为0,分区数必须大于0
     * @param key    发送消息的键
     * @param value    messageValue
     * @return
     */
    public Map<String,Object> sndMesForTemplate(String topic, Integer partitionNum, String key, String value){
        if(partitionNum != null && partitionNum != 0){
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result
                    = kafkaTemplate.send(topic, partitionIndex, key, value);
            return checkProRecord(result);
        }else{
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, value);
            return checkProRecord(result);
        }
    }

    /**
     * kafka发送消息模板
     * @param topic 主题
     * @param key 发送的键
     * @param value    messageValue
     * @return
     */
    public Map<String,Object> sndMesForTemplate(String topic, String key, String value){
        return this.sndMesForTemplate(topic, 0, key, value);
    }

    /**
     * 根据key值获取分区索引
     * @param key
     * @param partitionNum
     * @return
     */
    private int getPartitionIndex(String key, int partitionNum){
        if (key == null) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        }
        else {
            return Math.abs(key.hashCode())%partitionNum;
        }
    }

    /**
     * 检查发送返回结果record
     * @param res
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Map<String,Object> checkProRecord(ListenableFuture<SendResult<String, String>> res){
        Map<String,Object> m = new HashMap<>();
        if(res!=null){
            try {
                //检查result结果集
                SendResult r = res.get();
                //检查recordMetadata的offset数据，不检查producerRecord
                Long offsetIndex = r.getRecordMetadata().offset();
                if(offsetIndex!=null && offsetIndex>=0){
                    m.put("code", KafkaMesConstant.SUCCESS_CODE);
                    m.put("message", KafkaMesConstant.SUCCESS_MES);
                    return m;
                }else{
                    m.put("code", KafkaMesConstant.KAFKA_NO_OFFSET_CODE);
                    m.put("message", KafkaMesConstant.KAFKA_NO_OFFSET_MES);
                    return m;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }
        }else{
            m.put("code", KafkaMesConstant.KAFKA_NO_RESULT_CODE);
            m.put("message", KafkaMesConstant.KAFKA_NO_RESULT_MES);
            return m;
        }
    }
}
