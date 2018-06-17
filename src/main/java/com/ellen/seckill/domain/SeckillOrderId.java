package com.ellen.seckill.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SeckillOrderId implements Serializable {

    private Long productId;
    private String username;

    public SeckillOrderId() {

    }

    public SeckillOrderId(Long productId, String username) {
        this.productId = productId;
        this.username = username;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SeckillOrderId that = (SeckillOrderId) obj;
        if (productId != that.productId) {
            return false;
        }
        return username == that.username;
    }

    @Override
    public int hashCode() {
        return 31 * (productId == null ? 0 : productId.hashCode()) + (username == null ? 0 : username.hashCode());
    }
}
