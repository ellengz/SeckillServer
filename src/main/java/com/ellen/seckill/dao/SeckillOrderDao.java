package com.ellen.seckill.dao;

import com.ellen.seckill.domain.SeckillOrder;
import com.ellen.seckill.domain.SeckillOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeckillOrderDao extends JpaRepository<SeckillOrder, SeckillOrderId> {
    List<SeckillOrder> findBySeckillOrderIdUsername(String username);
}
