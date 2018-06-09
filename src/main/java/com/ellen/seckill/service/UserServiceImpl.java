package com.ellen.seckill.service;

import com.ellen.seckill.dao.UserDao;
import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.User;
import com.ellen.seckill.enums.UserEnum;
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
    public Result login(User user) {
        if(user.getUsername() != null && user.getEncryptPassword() != null) {
            User dbUser = findByUsername(user.getUsername());
            if(SecurityUtil.match(user.getEncryptPassword(), dbUser.getEncryptPassword())) {
                return ResultUtil.userSuccess("match");
            } else {
                return ResultUtil.userError(UserEnum.NO_MATCH);
            }
        } else {
            return ResultUtil.userError(UserEnum.SILLY);
        }
    }

    @Override
    public Result findAll() {
        return ResultUtil.userSuccess(userDao.findAll());
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
