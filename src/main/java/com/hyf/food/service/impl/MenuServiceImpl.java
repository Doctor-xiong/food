package com.hyf.food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.food.entity.Menu;
import com.hyf.food.mapper.MenuMapper;
import com.hyf.food.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	/***
	 * 查询推荐菜单
	 * @return
	 */
	@Override
	public List<Menu> queryMenuByPosition2() {
		return menuMapper.queryMenuByPosition2();
	}
	
	/***
	 * 查询所有菜单
	 * @return
	 */
	@Override
	public List<Menu> queryMenu() {
		return menuMapper.queryMenu();
	}
	
	/***
	 * 根据种类信息查询所有菜单
	 * @return
	 */
	@Override
	public List<Menu> queryMenuByType(String m_type) {
		return menuMapper.queryMenuByType(m_type);
	}
	
	/**
	 * 根据id获取菜品信息
	 * @param m_id
	 * @return
	 */
	@Override
	public Menu queryMenuById(long m_id) {
		return menuMapper.queryMenuById(m_id);
	}
	
	/***
	 * 修改菜品销量
	 * @param menu
	 * @return
	 */
	@Override
	public int updateMenuNumByMid(Menu menu) {
		return menuMapper.updateMenuNumByMid(menu);
	}
	
	/**
	* 传入菜品对象添加菜品
	* @param menu
	* @return
	*/
	@Override
	public int addMenu(Menu menu){
		return menuMapper.addMenu(menu);
	}
	/***
	* 按照分页获取内容
	* @param before
	* @param after
	* @return
	*/
	@Override
	public List<Menu> findAllPage(int before,int after) {
		return menuMapper.findAllPage(before, after);
	}
	
	/***
	* 计算有多少条数据
	* @return
	*/
	@Override
	public int count() {
		return menuMapper.count();
	}
	/**
	* 修改菜品状态为已下架
	* @return
	*/
	@Override
	public int deleteMenu(long m_id){
		return menuMapper.deleteMenu(m_id);
	}
	/**
	* 修改菜品状态为推荐
	* @return
	*/
	@Override
	public int specialMenu(long m_id){
		return menuMapper.specialMenu(m_id);
	}
	/**
	* 修改菜品状态为在售
	* @return
	*/
	@Override
	public int onsaleMenu(long m_id){
		return menuMapper.onsaleMenu(m_id);
	}
	/**
	* 修改菜品信息
	* @param menu
	* @return
	*/
	@Override
	public int updateMenu(Menu menu){
		return menuMapper.updateMenu(menu);
	}
	}
