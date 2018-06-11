package com.ellen.seckill.service;

import com.ellen.seckill.domain.Result;
import org.springframework.stereotype.Service;

@Service
public interface SeckillService {

    Result getList();
    Result getById(Long productId);
    Result getSecretKeyWithId(Long productId, String apiKey);
    Result executeSeckill(Long productId, String apiKey, String secretKey);
    int updateStock(Long productId);
}
