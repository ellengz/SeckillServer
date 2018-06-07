package com.ellen.seckill.dao;

import com.ellen.seckill.domain.SeckillProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeckillProductDao extends JpaRepository<SeckillProduct, Long>{
}
