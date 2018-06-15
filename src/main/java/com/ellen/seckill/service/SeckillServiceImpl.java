package com.ellen.seckill.service;

import com.ellen.seckill.dao.SeckillProductDao;
import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.SeckillProduct;
import com.ellen.seckill.enums.SeckillStateEnum;
import com.ellen.seckill.exception.SeckillException;
import com.ellen.seckill.util.ResultUtil;
import com.ellen.seckill.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillProductDao seckillProductDao;

    /**
     * get product list
     *
     * @return a result with product list
     */
    @Override
    public Result getList() {
        return ResultUtil.seckillSuccess(seckillProductDao.findAll());
    }

    /**
     * get product by productId
     *
     * @param productId
     * @return a result with a product
     */
    @Override
    public Result getById(Long productId) {
        return ResultUtil.seckillSuccess(seckillProductDao.findById(productId));
    }

    /**
     * generate a secret path based on productId and apiKey
     *
     * @param productId
     * @param apiKey
     * @return a result with a secret path
     */
    @Override
    public Result getSecretKeyWithId(Long productId, String apiKey) {
        Date now = new Date();
        if (seckillProductDao.findById(productId).isPresent()) {
            SeckillProduct product = seckillProductDao.findById(productId).get();
            if (now.compareTo(product.getStartTime()) < 0) {
                // not start yet, return system time
                return ResultUtil.seckillSuccess(now);
            } else if (now.compareTo(product.getEndTime()) > 0) {
                throw new SeckillException(SeckillStateEnum.END);
            }
            String secretPath = SecurityUtil.encrypt(productId + apiKey);
            return ResultUtil.seckillSuccess(secretPath);
        }

        throw new SeckillException(SeckillStateEnum.NOT_EXIST);
    }

    /**
     * update stock
     *
     * @param productId
     * @param apiKey
     * @param secretKey
     * @return a result
     */
    @Override
    public Result executeSeckill(Long productId, String apiKey, String secretKey) {
        if (SecurityUtil.match(productId + apiKey, secretKey)) {
            int code = updateStock(productId);
            switch (code) {
                case 0:
                    // TODO create and return SeckillOrder
                    return ResultUtil.seckillSuccess("where is the order?");
                case -1:
                    throw new SeckillException(SeckillStateEnum.NO_STOCK);
                case -2:
                    throw new RuntimeException();
            }
        }

        throw new SeckillException(SeckillStateEnum.DATA_REWRITE);
    }

    /**
     * try to update stock by minus 1
     *
     * @param productId
     * @return 0 if succeeded, -1 if no stock remaining, -2 if failed
     */
    @Override
    @Transactional
    public int updateStock(Long productId) {
        if (seckillProductDao.findById(productId).isPresent()) {
            SeckillProduct product = seckillProductDao.findById(productId).get();
            if (product.getNumber() == 0) {
                return -1;
            }
            product.setNumber(product.getNumber() - 1);
            seckillProductDao.save(product);
            return 0;
        }
        return -2;
    }
}
