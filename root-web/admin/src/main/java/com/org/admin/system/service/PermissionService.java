package com.org.admin.system.service;

import java.util.List;

import com.org.admin.system.entity.Permission;
import com.org.framework.data.BaseDataJpaService;

public interface PermissionService extends BaseDataJpaService<Permission, Integer> {

	/**
	 * 查询用户拥有的权限
	 * @param userId 用户id
	 * @return 结果集合
	 */
	public List<Permission> findPermissions(Integer userId);
	
	/**
	 * 查询所有的菜单
	 * @param userId
	 * @return 菜单集合
	 */
	public List<Permission> findMenus();
	
	
	/**
	 * 查询用户拥有的菜单权限
	 * @param userId
	 * @return 结果集合
	 */
	public List<Permission> findMenus(Integer userId);
	
	/**
	 * 查询菜单下的操作权限
	 * @param userId
	 * @return
	 */
	public List<Permission> findMenuOperation(Integer pid);
	
	/**
	 * 添加菜单基础操作
	 * @param pid 菜单id
	 * @param pName 菜单权限标识名
	 */
	public void addBaseOpe(Integer pid,String pClassName);
}
