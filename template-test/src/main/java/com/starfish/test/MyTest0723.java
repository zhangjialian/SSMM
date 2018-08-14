package com.starfish.test;

import com.alibaba.fastjson.JSON;
import com.starfish.common.CaiNiaoVirtualNoVO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BG349176
 * @date 2018/7/23 15:25
 */
public class MyTest0723 {
    /** 调用link的相关参数 **/
    //发送方的code
    private String logisticProviderId = "Tran_Store_13202710";
    //调用的api
    private String msgType = "CNSMS_VIRTUAL_NO_AXB_MAIL";
    //应用的secretKey
    private String secretKey = "123123321321";
    // 调用link的地址(正式)
    //private String url = "http://link.cainiao.com/gateway/link.do";
    // 调用link的地址(沙箱)
    //private String url = "https://linkdaily.tbsandbox.com/gateway/link.do";
    // 调用link的地址(彩虹桥)
    private String url = "https://link.tbsandbox.com/gateway/link.do";

    public void bindAXB() {
        CaiNiaoVirtualNoVO virtualNoVO = new CaiNiaoVirtualNoVO();
        //运单号
        virtualNoVO.setMailNO("3123123213");
        //公司id
        virtualNoVO.setCompanyId(123123123);
        //快递员的号码
        virtualNoVO.setPhoneB("18557536905");
        //绑定失效时间，由自己传入，最多1个月的时间(一天之后过期)
        virtualNoVO.setEndDate(DateUtils.addDays(new Date(), 1));
        virtualNoVO.setNeedRecord(false);
        //业务扩展参数
        virtualNoVO.setBizExtend("{callRestrict:0}");
        //在这里填写扩展字段，会透传
        virtualNoVO.setExtend("100001");

        //发送的内容
        String logisticsInterface = JSON.toJSONString(virtualNoVO);
        //签名
        String dataDigest = this.doSign(logisticsInterface, "utf-8",secretKey);

        List<BasicNameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("logistics_interface", logisticsInterface));
        formParams.add(new BasicNameValuePair("logistic_provider_id", logisticProviderId));
        formParams.add(new BasicNameValuePair("msg_type", msgType));
        formParams.add(new BasicNameValuePair("data_digest", dataDigest));

        String responseStr = null;
        try {
            UrlEncodedFormEntity entity = null;
            entity = new UrlEncodedFormEntity(formParams, "UTF-8");
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(entity);
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                String resEntityStr = EntityUtils.toString(response.getEntity());
                responseStr = new String(resEntityStr.getBytes("ISO-8859-1"), "UTF-8");
            } else {
                responseStr = "error:error reponse||status is " + response.getStatusLine().getStatusCode();
            }

        } catch (Exception e) {
            System.out.println("exception");
        }
        System.out.println("The response is: " + responseStr);
    }

    private String doSign(String content, String charset, String secretKey) {
        String sign = "";
        content = content + secretKey;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes(charset));
            sign = new String(Base64.encodeBase64(md.digest()), charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sign;
    }

    public static void main(String args[]){
        MyTest0723 virtualNo = new MyTest0723();
        virtualNo.bindAXB();
    }

}
