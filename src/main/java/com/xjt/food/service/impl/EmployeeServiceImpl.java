package com.xjt.food.service.impl;

import java.util.List;

import com.xjt.food.entity.Employee;
import com.xjt.food.mapper.EmployeeMapper;
import com.xjt.food.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;

	/***
	 * 添加新员工
	 * @param employee
	 * @return
	 */
	@Override
	public int addEmployeeMsg(Employee employee) {
		return employeeMapper.addEmployeeMsg(employee);
	}

	/***
	 * 删除员工信息
	 * @param e_id
	 * @return
	 */
	@Override
	public int deleteEmployeeMsg(long e_id) {
		return employeeMapper.deleteEmployeeMsg(e_id);
	}

	/**
	 * 修改员工信息
	 * @param employee
	 * @return
	 */
	@Override
	public int updateEmployeeMsg(Employee employee) {
		return employeeMapper.updateEmployeeMsg(employee);
	}

	/***
	 * 按id查询员工信息
	 * @param e_id
	 * @return
	 */
	@Override
	public Employee queryEmloyeeById(long e_id) {
		return employeeMapper.queryEmloyeeById(e_id);
	}

	/***
	 * 查询所有员工信息
	 * @return
	 */
	@Override
	public List<Employee> queryAllEmployeeMsg() {
		return employeeMapper.queryAllEmployeeMsg();
	}

	/***
	 * 员工登录
	 * @param e_password
	 * @return
	 */
	@Override
	public Employee EmployeeLogin(long e_id, String e_password) {
		return employeeMapper.EmployeeLogin(e_id, e_password);
	}
	/***
	 * 按电话查询员工信息
	 * @param e_tel
	 * @return
	 */
	@Override
	public Employee queryEmployeeByTel(long e_tel) {
		return employeeMapper.queryEmployeeByTel(e_tel);
	}

	/***
	 * 按状态查询员工信息
	 * @param e_position
	 * @return
	 */
	@Override
	public List<Employee> queryEmployeeByPosition(long e_position) {
		return employeeMapper.queryEmployeeByPosition(e_position);
	}
	
	/***
	 * 按照分页获取内容
	 * @param before
	 * @param after
	 * @return
	 */
	@Override
	public List<Employee> findAllPage(int before,int after) {
		return employeeMapper.findAllPage(before, after);
	}
	
	/***
	 * 计算有多少条数据
	 * @return
	 */
	@Override
	public int count() {
		return employeeMapper.count();
	}

}
