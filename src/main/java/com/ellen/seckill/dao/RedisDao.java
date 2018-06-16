package com.ellen.seckill.dao;

import com.ellen.seckill.domain.SeckillProduct;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository
public class RedisDao {

    // threadsafe
    private final JedisPool jedisPool;

    public RedisDao() {
        // use default host (localhost) and port (6379)
        jedisPool = new JedisPool();
    }

    /**
     * schema is used to encapsulate:
     * the (de)serialization logic of an object
     * the validation of an object’s required fields
     * the mapping of an object’s field names to field numbers
     * the instantiation of the object.
     */
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
    public String putProduct(SeckillProduct product) throws RuntimeException {
        Jedis jedis = jedisPool.getResource();
        try {
            String key = "productId:" + product.getProductId();
            // serialization
            byte[] bytes = ProtobufIOUtil.toByteArray(product, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            // discard cache after timeout
            int timeout = 30 * 60;
            // return OK or error message
            String result = jedis.setex(key.getBytes(), timeout, bytes);
            return result;
        } finally {
            jedis.close();
        }
    }
}
