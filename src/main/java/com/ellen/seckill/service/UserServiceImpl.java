package com.ellen.seckill.service;

import com.ellen.seckill.dao.UserDao;
import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.User;
import com.ellen.seckill.util.ResultUtil;
import com.ellen.seckill.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    @Override
    public Result register(User user) {
        user.setEncryptPassword(SecurityUtil.encrypt(user.getEncryptPassword()));
        return ResultUtil.userSuccess(userDao.save(user));
    }

    @Override
    public Result login(String token) {
        return null;
    }

    @Override
    public Result findAll() {
        return ResultUtil.userSuccess(userDao.findAll());
    }


}
