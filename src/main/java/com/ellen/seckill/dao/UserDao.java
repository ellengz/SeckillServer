package com.ellen.seckill.dao;

import com.ellen.seckill.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
    User findUserByUsername(String username);
    User findUserByFbToken(String fbToken);
}
