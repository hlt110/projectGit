package com.sun.szk.service.impl;

import java.io.Serializable;

import com.sun.szk.base.core.exception.ServiceException;
import com.sun.szk.base.core.service.impl.ServiceSupport;
import com.sun.szk.dao.SysResourceMapper;
import com.sun.szk.domain.SysResource;
import com.sun.szk.service.SysResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysResourceService")
public class SysResourceServiceImpl extends ServiceSupport<SysResource, SysResourceMapper> implements SysResourceService {
	@Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
	public SysResourceMapper getMapper() {
		return sysResourceMapper;
	}

    @Override
    public void delete(Serializable id) throws ServiceException {
        getMapper().delete(SysResource.class, id);
    }

    @Override
    public SysResource find(Serializable id) throws ServiceException {
        return getMapper().selectById(SysResource.class,id);
    }
}
