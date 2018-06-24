package com.ellen.seckill.service;

import com.ellen.seckill.domain.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getList() {
        Result result = seckillService.getList();
        assertNotNull(result.getData());
    }

    @Test
    public void executeSeckill() {
        Long productId = Long.valueOf(1001);
        String apiKey = "$2a$10$mnlr96p/R.k.Py/oKx6HNeFFrVDphVOJAtP2C7UcNDGF2KFfBC1yq";
        Result secretKey = seckillService.getSecretKeyWithId(productId, apiKey);
        System.out.println(secretKey.getData());
//        String username = "hello";
//        Result execution = seckillService.executeSeckill(productId, apiKey, secretKey.getData().toString(), username);
//        assertEquals("200", execution.getCode().toString());
    }
}