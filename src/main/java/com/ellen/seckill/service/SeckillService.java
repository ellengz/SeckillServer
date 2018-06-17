package com.ellen.seckill.service;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.SeckillOrder;
import com.ellen.seckill.domain.SeckillProduct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SeckillService {

    Result getList();
    Result getSecretKeyWithId(Long productId, String apiKey);
    Result executeSeckill(Long productId, String apiKey, String secretKey, String userPhone);
    SeckillProduct getById(Long productId);
    SeckillOrder createOrder(Long productId, String username);
    Result getOrder(Long productId, String username);
    Result getOrderList(String username);
    int updateStock(Long productId);
}
