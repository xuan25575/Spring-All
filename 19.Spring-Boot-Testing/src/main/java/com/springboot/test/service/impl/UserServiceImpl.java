package com.springboot.test.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.test.domain.User;
import com.springboot.test.mapper.UserMapper;
import com.springboot.test.service.UserService;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {



}
