package com.sun.szk.ssm.service.impl;

import java.io.Serializable;

import com.sun.szk.ssm.base.exception.ServiceException;
import com.sun.szk.ssm.base.service.impl.ServiceSupport;
import com.sun.szk.ssm.dao.UserMapper;
import com.sun.szk.ssm.domain.User;
import com.sun.szk.ssm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceSupport<User, UserMapper> implements UserService {
	@Autowired
    private UserMapper userMapper;

    @Override
	public UserMapper getMapper() {
		return userMapper;
	}

    @Override
    public void delete(Serializable id) throws ServiceException {
        getMapper().delete(User.class, id);
    }

    @Override
    public User find(Serializable id) throws ServiceException {
        return getMapper().selectById(User.class,id);
    }
}
