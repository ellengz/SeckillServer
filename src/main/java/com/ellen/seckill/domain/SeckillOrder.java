package com.ellen.seckill.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class SeckillOrder {

    @EmbeddedId
    private SeckillOrderId seckillOrderId;
    private byte state;
    private Date createTime;

    public SeckillOrderId getSeckillOrderId() {
        return seckillOrderId;
    }

    public void setSeckillOrderId(SeckillOrderId seckillOrderId) {
        this.seckillOrderId = seckillOrderId;
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
                "productId=" + seckillOrderId.getProductId() +
                "userPhone=" + seckillOrderId.getUserPhone() +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
