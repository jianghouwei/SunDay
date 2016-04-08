package com.org.admin.system.service.impl;

import org.springframework.stereotype.Service;

import com.org.admin.system.entity.Log;
import com.org.admin.system.service.LogService;
import com.org.framework.data.BaseDataJpaServiceImpl;

@Service
public class LogServiceImpl extends BaseDataJpaServiceImpl<Log, Integer> implements LogService{

}
