package com.xjt.food.action;

import com.xjt.food.entity.Admin;
import com.xjt.food.entity.Employee;
import com.xjt.food.service.IAdminService;
import com.xjt.food.service.IDeskService;
import com.xjt.food.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class loginAction {
	
	@Autowired
	private IAdminService adminServiceImpl;
	
	@Autowired
	private IEmployeeService employeeServiceImpl;

	@Autowired
	private IDeskService deskService;
	/***
	 * 按ID查询员工信息
	 * @param model
	 * @return
	 */
	@RequestMapping("AdminAndEmployeelogin.action")
	public String AdminAndEmployeelogin(Model model,HttpSession session,String username,String password,String role){
		log.info("-----------------------"+username);
		Admin admin = null;
		Employee emp = null;
		if(role.equals("admin")){
			admin = adminServiceImpl.Adminlogin(username, password);
			session.setAttribute("admin", admin);
			session.removeAttribute("emp");
		}else if(role.equals("employee")){
			long etel = Long.parseLong(username);
			emp = employeeServiceImpl.EmployeeLogin(etel,password);
			session.setAttribute("emp", emp);
			session.removeAttribute("admin");
		}
		if(admin != null || emp != null){
			log.info("登录中——————————————————————");
			return "countDesk.action";
		}else{
			model.addAttribute("error", "登录失败！用户名或密码错误。。。");
			return "service/login.jsp";
		}
		
	}
	
	/***
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("loginOut.action")
	public String loginOut(HttpSession session){
		log.info("退出管理系统——————————————————--");
		session.invalidate();
		return "service/login.jsp";
	}

	/**
	 * 用户退出时候的操作
	 */
	@RequestMapping("myLoginOut.action")
	public String loginOut(@RequestParam("d_id") Long id,Model model){
		if(ObjectUtils.isEmpty(id) ||  id < 0){
			return "clientLogin.jsp";
		}
		int res = deskService.changeUserStatus(id);
		if(res < 0){
			model.addAttribute("error","改变用户状态时候失败");
			return "error.jap";
		}
		return "clientLogin.jsp";
	}
}
