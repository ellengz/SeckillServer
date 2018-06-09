package com.ellen.seckill.service;

import com.ellen.seckill.dao.UserDao;
import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.User;
import com.ellen.seckill.enums.UserStateEnum;
import com.ellen.seckill.exception.UserException;
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

    /**
     * register a new user
     *
     * @param user
     * @return result
     */
    @Override
    public Result register(User user) {
        if(findByUsername(user.getUsername()) == null) {
            user.setEncryptPassword(SecurityUtil.encrypt(user.getEncryptPassword()));
            return ResultUtil.userSuccess(userDao.save(user));
        } else {
            throw new UserException(UserStateEnum.REPEAT_USERNAME);
        }
    }

    /**
     * user login
     *
     * @param user
     * @return result
     */
    @Override
    public Result login(User user) {
        // null params will throw NO_MATCH error
        User dbUser = findByUsername(user.getUsername());
        if(dbUser != null && SecurityUtil.match(user.getEncryptPassword(), dbUser.getEncryptPassword())) {
            //TODO should return token/api_key
            return ResultUtil.userSuccess("match");
        } else {
            // user not exists or username-password pair not match
            throw new UserException(UserStateEnum.NO_MATCH);
        }
    }

    /**
     * find user by username
     *
     * @param username
     * @return user
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
