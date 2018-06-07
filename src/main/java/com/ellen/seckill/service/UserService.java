package com.ellen.seckill.service;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Result register(User user);
    Result login(String token);

}
