package com.org.admin.system.service.impl;

import org.springframework.stereotype.Service;

import com.org.admin.system.entity.Dict;
import com.org.admin.system.service.DictService;
import com.org.framework.data.BaseDataJpaServiceImpl;

@Service
public class DictServiceImpl extends BaseDataJpaServiceImpl<Dict, Integer> implements DictService{

}
