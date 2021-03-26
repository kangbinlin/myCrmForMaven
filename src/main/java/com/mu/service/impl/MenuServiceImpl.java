package com.mu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mu.bean.Menu;
import com.mu.mapper.MenuMapper;
import com.mu.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService {
@Autowired
	private MenuMapper menuMapper;

@Override
public List<Menu> queryForMenu() {
	// TODO Auto-generated method stub
	return menuMapper.queryForMenu();
}
	
}
