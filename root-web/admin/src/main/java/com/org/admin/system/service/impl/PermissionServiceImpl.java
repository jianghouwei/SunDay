package com.org.admin.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.org.admin.system.dao.PermissionDao;
import com.org.admin.system.entity.Permission;
import com.org.admin.system.service.PermissionService;
import com.org.framework.data.BaseDataJpaServiceImpl;

public class PermissionServiceImpl extends BaseDataJpaServiceImpl<Permission, Integer>implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> findPermissions(Integer userId) {
		return permissionDao.findPermissions(userId);
	}

	@Override
	public List<Permission> findMenus() {
		return permissionDao.findMenus();
	}

	@Override
	public List<Permission> findMenus(Integer userId) {
		return permissionDao.findMenus(userId);
	}

	@Override
	public List<Permission> findMenuOperation(Integer pid) {
		return permissionDao.findMenuOperation(pid);
	}

	@Override
	public void addBaseOpe(Integer pid, String pClassName) {
		List<Permission> pList = new ArrayList<Permission>();
		pList.add(new Permission(pid, "添加", "O", "", "sys:" + pClassName + ":add"));
		pList.add(new Permission(pid, "删除", "O", "", "sys:" + pClassName + ":delete"));
		pList.add(new Permission(pid, "修改", "O", "", "sys:" + pClassName + ":update"));
		pList.add(new Permission(pid, "查看", "O", "", "sys:" + pClassName + ":view"));

		// 添加没有的基本操作
		List<Permission> existPList = findMenuOperation(pid);
		for (Permission permission : pList) {
			boolean exist = false;
			for (Permission existPermission : existPList) {
				if (permission.getPermCode().equals(existPermission.getPermCode())) {
					exist = true;
					break;
				} else {
					exist = false;
				}
			}
			if (!exist)
				save(permission);
		}
	}
}
