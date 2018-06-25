package com.ellen.seckill.service;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.SeckillProduct;
import com.ellen.seckill.exception.SeckillException;
import org.json.simple.JSONObject;
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

    /**
     * execution should succeed
     * stock should be updated
     * order should be created
     */
    @Test
    public void executeSeckill() {
        Long productId = Long.valueOf(1000);
        String userToken = "test";
        Result secretKey = seckillService.getSecretKeyWithId(productId, userToken);
        String username = "test";
        JSONObject data = (JSONObject) secretKey.getData();
        Result result = seckillService.executeSeckill(productId, userToken, data.get("secretKey").toString(), username);
        assertEquals(200, (long) result.getCode()); // success
        assertEquals(99, seckillService.getById(productId).getNumber()); // update stock
        assertNotNull(seckillService.getOrderList("test")); // create order
    }
}