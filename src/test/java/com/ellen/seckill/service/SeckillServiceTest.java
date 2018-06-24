package com.ellen.seckill.service;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.SeckillProduct;
import com.ellen.seckill.exception.SeckillException;
import org.json.simple.JSONObject;
import org.junit.Rule;
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
    public void getById() {
        Long id = Long.valueOf(1001);
        SeckillProduct product = seckillService.getById(id);
        assertNotNull(product);
        assertEquals((long) id, product.getProductId());

    }

    @Test
    public void executeSeckill() {
        // illegal yet repeated order
        try{
            Long productId = Long.valueOf(1001);
            String apiKey = "$2a$10$mnlr96p/R.k.Py/oKx6HNeFFrVDphVOJAtP2C7UcNDGF2KFfBC1yq";
            Result secretKey = seckillService.getSecretKeyWithId(productId, apiKey);
            String username = "hello";
            JSONObject data = (JSONObject) secretKey.getData();
            seckillService.executeSeckill(productId, apiKey, data.get("secretKey").toString(), username);
            fail();
        }catch (SeckillException e) {
            assertEquals("402", e.getCode().toString());
        }
    }
}