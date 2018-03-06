package com.sun.szk.ssm.thread;

import java.util.Date;

import javax.annotation.Resource;

import com.sun.szk.ssm.base.exception.ServiceException;
import com.sun.szk.ssm.domain.User;
import com.sun.szk.ssm.service.UserService;
import com.sun.szk.ssm.service.impl.UserServiceImpl;

public class UserThread extends Thread{
	
	private User user;
	private UserService userService;
	
	private int  start;
	private int  length;
	
	public UserThread(User user,UserService userService,int start,int length){
		this.user = user;
		this.userService = userService;
		this.start = start;
		this.length = length;
		
	}

	/**
	 * user管理服务类
	 */
	@Override
	public void run() {
		
		try {
			for(int i=start;i<start+length;i++){
				System.out.println("起始条数："+start);
				System.out.println("结束条数："+(start+length));
				 user = new User();
				 user.setAddress(i+"");
				 user.setBirthday(new Date());
				 user.setPassword(i+"");
				 user.setSex(1+"");
				 user.setUsername(1+"");
				 userService.save(user);
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
