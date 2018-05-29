package com.ellen.seckill.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SeckillOrderCompositeKey implements Serializable{

    private Long productId;
    private Long userPhone;

    public SeckillOrderCompositeKey() {

    }

    public SeckillOrderCompositeKey(Long productId, Long userPhone) {
        this.productId = productId;
        this.userPhone = userPhone;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SeckillOrderCompositeKey that = (SeckillOrderCompositeKey) obj;
        if (productId != that.productId) {
            return false;
        }
        return userPhone == that.userPhone;
    }

    @Override
    public int hashCode() {
        return 31 * (productId == null? 0 : productId.hashCode()) + (userPhone == null? 0 : userPhone.hashCode());
    }
}
