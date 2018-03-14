package com.sun.szk.controller;

import com.sun.szk.domain.SysUser;
import com.sun.szk.service.SysUserService;
import com.sun.szk.base.core.annotations.LogContent;
import com.sun.szk.base.core.controller.BaseController;
import com.sun.szk.base.core.controller.form.QueryAssistor;
import com.sun.szk.base.core.result.HttpResultEntity;
import com.sun.szk.base.core.result.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * sys_user管理控制器
 *
 */
@Controller
@RequestMapping("/service/sysUser")
public class SysUserController extends BaseController {


	/**
	 * sys_user管理服务类
	 */
	@Resource
	private SysUserService sysUserService;

	/**
	 * 列出sys_user管理
	 */
	@LogContent("查询sys_user管理")
	@RequestMapping("/list")
	@ResponseBody
	public QueryResult<SysUser> list(SysUser sysUser , QueryAssistor queryAssistor) throws Exception {
		QueryResult<SysUser> s = sysUserService.getData(sysUser,queryAssistor.configPage().addOrderByDesc("createDateTime"));
		return s;
	}


	/**
	 * 添加sys_user管理
	 */
	@LogContent("添加sys_user管理")
	@RequestMapping("/add")
	@ResponseBody
	public HttpResultEntity<?> add(SysUser sysUser) throws Exception {
		sysUserService.save(sysUser);
        return getSuccessResult();
	}

	/**
	 * 编辑sys_user管理
	 */
    @LogContent("编辑sys_user管理")
	@RequestMapping("/edit")
    @ResponseBody
	public HttpResultEntity<?> edit(SysUser sysUser) throws Exception {
		sysUserService.updateForSelective(sysUser);
        return getSuccessResult();
	}

	/**
	 * 删除单个sys_user管理
	 */
	@LogContent("删除sys_user管理")
	@RequestMapping("/delete")
	@ResponseBody
	public HttpResultEntity<?> delete(Integer userid)  throws Exception{
		sysUserService.delete(userid);
		return getSuccessResult();
	}

	/**
	 * 列出sys_user管理
	 */
	@LogContent("查询sys_user管理")
	@RequestMapping("/list1")
	@ResponseBody
	public List<SysUser> list1(SysUser sysUser , QueryAssistor queryAssistor) throws Exception {
		List<SysUser> s = sysUserService.findAll(sysUser);
		return s;
	}
}
