package com.starfish.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author BG349176
 * @date 2018/7/24 11:26
 */
public class CaiNiaoVirtualNoVO implements Serializable {
    private static final long serialVersionUID = 5230291977130190167L;

    /**
     * 运单号
     */
    private String mailNO;

    /**
     * 公司ID
     */
    private long companyId;

    /**
     * 快递员手机号码
     */
    private String phoneB;

    /**
     * 小号绑定的结束日期
     */
    private Date endDate;

    /**
     * 是否需要录音
     */
    private boolean needRecord;

    /**
     * 业务参数扩展字段，可为空或JSON字符串
     */
    private String bizExtend;

    /**
     * 业务扩展字段，不能超过100字符
     */
    private String extend;

    public String getMailNO() {
        return mailNO;
    }

    public void setMailNO(String mailNO) {
        this.mailNO = mailNO;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getPhoneB() {
        return phoneB;
    }

    public void setPhoneB(String phoneB) {
        this.phoneB = phoneB;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isNeedRecord() {
        return needRecord;
    }

    public void setNeedRecord(boolean needRecord) {
        this.needRecord = needRecord;
    }

    public String getBizExtend() {
        return bizExtend;
    }

    public void setBizExtend(String bizExtend) {
        this.bizExtend = bizExtend;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }
}
