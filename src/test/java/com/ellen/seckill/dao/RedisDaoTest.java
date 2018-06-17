package com.ellen.seckill.dao;

import com.ellen.seckill.domain.SeckillProduct;
import com.ellen.seckill.service.SeckillService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

    private long id = 1001;
    private static SeckillProduct product;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testRedis() throws RuntimeException {
        product = redisDao.getProductById(id);
        if (product == null) {
            testRedisPut();
            testRedisDel();
        } else {
            testRedisDel();
            testRedisPut();
        }
    }

    public void testRedisPut() throws RuntimeException {
        product = seckillService.getById(id);
        if (product != null) {
            Assert.assertEquals(redisDao.putProduct(product), "OK");
        }
    }

    public void testRedisDel() throws RuntimeException {
        redisDao.deleteProductById(id);
        Assert.assertEquals(redisDao.getProductById(id), null);
    }


}