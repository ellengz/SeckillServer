package com.ellen.seckill.controller;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private SeckillService seckillService;

    @GetMapping(value = "/product")
    public Result getList() {
        return seckillService.getList();
    }

    @PostMapping(value = "/product/{productId}")
    public Result getSecretUrl(@PathVariable("productId") Long productId,
                                 @RequestParam("apiKey") String apiKey) {
        return seckillService.getSecretKeyWithId(productId, apiKey);
    }

    @PostMapping(value = "/product/{productId}/execution")
    public Result executeSeckill(@PathVariable("productId") Long productId,
                                 @RequestParam("secretPath") String secretPath,
                                 @RequestParam("apiKey") String apiKey) {
        return seckillService.executeSeckill(productId, apiKey, secretPath);
    }
}
