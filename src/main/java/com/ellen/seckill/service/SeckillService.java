package com.ellen.seckill.service;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.SeckillProduct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SeckillService {

    Result getList();
    Result getSecretKeyWithId(Long productId, String apiKey);
    Result executeSeckill(Long productId, String apiKey, String secretKey);
    SeckillProduct getById(Long productId);
    int updateStock(Long productId);
}
