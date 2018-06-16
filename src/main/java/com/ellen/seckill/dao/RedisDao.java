package com.ellen.seckill.dao;

import com.ellen.seckill.domain.SeckillProduct;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository
public class RedisDao {

    private final JedisPool jedisPool;
    private final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    public RedisDao() {
//        jedisPool = new JedisPool(ip, port);
        jedisPool = new JedisPool();
    }

    private RuntimeSchema<SeckillProduct> schema = RuntimeSchema.createFrom(SeckillProduct.class);

    /**
     * get a SeckillProduct from Redis
     *
     * @param productId
     * @return null or SeckillProduct
     * @throws RuntimeException
     */
    public SeckillProduct getProductById(Long productId) throws RuntimeException {
        Jedis jedis = jedisPool.getResource();
        try {
            String key = "productId:" + productId;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                SeckillProduct seckillProduct = schema.newMessage(); // empty
                // deserialization
                // put data into seckillProduct using the schema
                ProtobufIOUtil.mergeFrom(bytes, seckillProduct, schema);
                return seckillProduct;
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * put a SeckillProdcut in Redis
     *
     * @param product
     * @return a string
     * @throws RuntimeException
     */
    public String putProduct(SeckillProduct product) throws RuntimeException{
        Jedis jedis = jedisPool.getResource();
        try {
            String key = "productId:" + product.getProductId();
            // serialization
            byte[] bytes = ProtobufIOUtil.toByteArray(product, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            int timeout = 60 * 60;
            // return OK or error message
            String result = jedis.setex(key.getBytes(), timeout, bytes);
            return result;
        } finally {
            jedis.close();
        }
    }
}
