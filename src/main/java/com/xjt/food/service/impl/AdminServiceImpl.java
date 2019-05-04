package com.xjt.food.service.impl;

import com.xjt.food.entity.Admin;
import com.xjt.food.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjt.food.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	/***
	 * 修改管理员信息
	 * @param admin
	 * @return
	 */
	@Override
	public int updateAdminMsg(Admin admin) {
		return adminMapper.updateAdminMsg(admin);
	}
	
	/***
	 * 管理员登录
	 * @return
	 */
	@Override
	public Admin Adminlogin(String username,String password) {
		return adminMapper.Adminlogin(username,password);
	}
	/***
	 * 修改管理员密码
	 * @param admin
	 * @return
	 */
	@Override
	public int updateAdminPsw(Admin admin) {
		return adminMapper.updateAdminPsw(admin);
	}

}
