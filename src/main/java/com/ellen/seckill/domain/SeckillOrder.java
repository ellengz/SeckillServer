package com.ellen.seckill.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class SeckillOrder {

    @EmbeddedId
    private SeckillOrderCompositeKey seckillOrderCompositeKey;
    private byte state;
    private Date createTime;

    public SeckillOrderCompositeKey getSeckillOrderCompositeKey() {
        return seckillOrderCompositeKey;
    }

    public void setSeckillOrderCompositeKey(SeckillOrderCompositeKey seckillOrderCompositeKey) {
        this.seckillOrderCompositeKey = seckillOrderCompositeKey;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SeckillOrder{" +
                "productId=" + seckillOrderCompositeKey.getProductId() +
                "userPhone=" + seckillOrderCompositeKey.getUserPhone() +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
