package com.mu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mu.bean.Log;
import com.mu.mapper.LogMapper;
import com.mu.service.LogService;
@Service
public class LogServiceImpl implements LogService {
@Autowired
	private LogMapper LogMapper;
	@Override
	public int insert(Log log) {
		// TODO Auto-generated method stub
		return LogMapper.insert(log);
	}

}
