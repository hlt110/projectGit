package com.sun.szk.controller;

import com.sun.szk.service.SysResourceService;
import com.sun.szk.base.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * sys_resource管理控制器
 *
 */
@Controller
@RequestMapping("/service/sysResource")
public class SysResourceController extends BaseController {


	/**
	 * sys_resource管理服务类
	 */
	@Resource
	private SysResourceService sysResourceService;

//	/**
//	 * 列出sys_resource管理
//	 */
//	@LogContent("查询sys_resource管理")
//	@RequestMapping("/list")
//	@ResponseBody
//	public QueryResult<SysResource> list(SysResource sysResource ,QueryAssistor queryAssistor) throws Exception {
//        return sysResourceService.getData(sysResource,queryAssistor.configPage().addOrderByDesc("createDateTime"));
//
//	}
//
//
//	/**
//	 * 添加sys_resource管理
//	 */
//	@LogContent("添加sys_resource管理")
//	@RequestMapping("/add")
//	@ResponseBody
//	public HttpResultEntity<?> add(SysResource sysResource) throws Exception {
//		sysResourceService.save(sysResource);
//        return getSuccessResult();
//	}
//
//	/**
//	 * 编辑sys_resource管理
//	 */
//    @LogContent("编辑sys_resource管理")
//	@RequestMapping("/edit")
//    @ResponseBody
//	public HttpResultEntity<?> edit(SysResource sysResource) throws Exception {
//		sysResourceService.updateForSelective(sysResource);
//        return getSuccessResult();
//	}
//
//	/**
//	 * 删除单个sys_resource管理
//	 */
//	@LogContent("删除sys_resource管理")
//	@RequestMapping("/delete")
//	@ResponseBody
//	public HttpResultEntity<?> delete(Integer resourceid)  throws Exception{
//		sysResourceService.delete(resourceid);
//		return getSuccessResult();
//	}
}
