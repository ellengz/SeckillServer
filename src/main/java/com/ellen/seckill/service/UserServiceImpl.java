package com.ellen.seckill.service;

import com.ellen.seckill.dao.UserDao;
import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.User;
import com.ellen.seckill.enums.UserStateEnum;
import com.ellen.seckill.exception.UserException;
import com.ellen.seckill.util.CommonUtil;
import com.ellen.seckill.util.ResultUtil;
import com.ellen.seckill.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    /**
     * register a new user
     *
     * @param user
     * @return a result
     */
    @Override
    public Result register(User user) {
        if(findByUsername(user.getUsername()) == null) {
            user.setEncryptPassword(SecurityUtil.encrypt(user.getEncryptPassword()));
            Date now = new Date();
            user.setCreateTime(now);
            userDao.save(user);
            return ResultUtil.userSuccess();
        } else {
            throw new UserException(UserStateEnum.REPEAT_USERNAME);
        }
    }

    /**
     * user login
     *
     * @param user
     * @return a result with api key
     */
    @Override
    public Result login(User user) {
        User dbUser = findByUsername(user.getUsername());
        if(dbUser != null && SecurityUtil.match(user.getEncryptPassword(), dbUser.getEncryptPassword())) {
            String apiKey = SecurityUtil.getApiKey();
            dbUser.setApiKey(apiKey);
            userDao.save(dbUser); // update database
            return ResultUtil.userSuccess(apiKey);
        } else {
            // null params, user not exists and username-password not match
            throw new UserException(UserStateEnum.NO_MATCH);
        }
    }

    /**
     * find user by username
     *
     * @param username
     * @return the user
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
