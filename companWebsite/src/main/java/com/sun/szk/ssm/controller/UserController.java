package com.sun.szk.ssm.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.szk.ssm.base.annotations.LogContent;
import com.sun.szk.ssm.base.controller.BaseController;
import com.sun.szk.ssm.base.exception.ServiceException;
import com.sun.szk.ssm.base.result.HttpResultEntity;
import com.sun.szk.ssm.domain.User;
import com.sun.szk.ssm.service.UserService;
import com.sun.szk.ssm.thread.UserThread;

/**
 * user管理控制器
 *
 */
@Controller
@RequestMapping("/service/user")
public class UserController extends BaseController {


	/**
	 * user管理服务类
	 */
	@Resource(name="userService")
	private UserService userService;

	/**
	 * 列出user管理
	 */
//	@LogContent("查询user管理")
//	@RequestMapping("/list")
//	@ResponseBody
//	public QueryResult<User> list(User user ,QueryAssistor queryAssistor) throws Exception {
//        return userService.getData(user,queryAssistor.configPage().addOrderBy("createDateTime","desc"));
//
//	}

//
//	/**
//	 * 添加user管理
//	 */
	@LogContent("添加user管理")
	@RequestMapping("/add")
	@ResponseBody
	public HttpResultEntity<?> add(User user) throws Exception {
		String[] a = new String[100];
		for(int i = 0;i<4;i++){
			 UserThread u  = new UserThread(user,userService,a.length/4*i,a.length/4);
			 u.start();
			 //u.join();
		}
		
		//new Thread(){}.start();
		
        return getSuccessResult();
	}
//
//	/**
//	 * 编辑user管理
//	 */
//    @LogContent("编辑user管理")
//	@RequestMapping("/edit")
//    @ResponseBody
//	public HttpResultEntity<?> edit(User user) throws Exception {
//		userService.updateForSelective(user);
//        return getSuccessResult();
//	}
//
//	/**
//	 * 删除单个user管理
//	 */
//	@LogContent("删除user管理")
//	@RequestMapping("/delete")
//	@ResponseBody
//	public HttpResultEntity<?> delete(Integer id)  throws Exception{
//		userService.delete(id);
//		return getSuccessResult();
//	}
}
