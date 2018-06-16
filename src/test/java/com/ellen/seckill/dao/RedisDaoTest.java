package com.ellen.seckill.dao;

import com.ellen.seckill.domain.SeckillProduct;
import com.ellen.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

    private long id = 1001;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testRedis() throws RuntimeException{
        SeckillProduct product = redisDao.getProductById(id);
        if (product == null) {
            product = seckillService.getById(id);
            if (product != null) {
                System.out.println(redisDao.putProduct(product));
                System.out.println(redisDao.getProductById(id));
            }
        }

    }


}