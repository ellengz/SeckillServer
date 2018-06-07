package com.ellen.seckill.dao;

import com.ellen.seckill.domain.SeckillOrder;
import com.ellen.seckill.domain.SeckillOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeckillOrderDao extends JpaRepository<SeckillOrder, SeckillOrderId> {

}
