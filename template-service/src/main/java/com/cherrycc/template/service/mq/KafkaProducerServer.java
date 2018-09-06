package com.cherrycc.template.service.mq;

import com.alibaba.fastjson.JSON;
import com.cherrycc.template.common.mq.KafkaMesConstant;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author BG349176
 * @date 2018/9/6 18:11
 */
public class KafkaProducerServer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * kafka发送消息模板
     * @param topic 主题
     * @param value    messageValue
     * @param ifPartition 是否使用分区 0是\1不是
     * @param partitionNum 分区数 如果是否使用分区为0,分区数必须大于0
     * @param role 角色:bbc app erp...
     */
    public Map<String,Object> sndMesForTemplate(String topic, Object value, String ifPartition,
                                                Integer partitionNum, String role){
        String key = role+"-"+value.hashCode();
        String valueString = JSON.toJSONString(value);
        if("0".equals(ifPartition)){
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partitionIndex, key, valueString);
            return checkProRecord(result);
        }else{
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, valueString);
            return checkProRecord(result);
        }
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
