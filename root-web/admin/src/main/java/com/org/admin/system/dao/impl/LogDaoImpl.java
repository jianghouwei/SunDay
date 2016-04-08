package com.org.admin.system.dao.impl;

import org.springframework.stereotype.Service;

import com.org.admin.system.entity.Dict;
import com.org.framework.data.BasePageRepository;
import com.org.framework.data.BasePageRepositoryImpl;

@Service
public class LogDaoImpl extends BasePageRepositoryImpl<Dict, Integer> implements BasePageRepository<Dict, Integer> {

}
