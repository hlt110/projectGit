package com.sun.szk.service.impl;

import com.sun.szk.dao.SysUserMapper;
import com.sun.szk.domain.SysUser;
import com.sun.szk.service.SysUserService;
import com.sun.szk.base.core.exception.ServiceException;
import com.sun.szk.base.core.service.impl.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceSupport<SysUser, SysUserMapper> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
	public SysUserMapper getMapper() {
		return sysUserMapper;
	}

    @Override
    public void delete(Serializable id) throws ServiceException {
        getMapper().delete(SysUser.class, id);
    }

    @Override
    public SysUser find(Serializable id) throws ServiceException {
        return getMapper().selectById(SysUser.class,id);
    }
}
