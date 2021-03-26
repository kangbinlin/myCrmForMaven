package com.mu.mapper;

import java.util.List;

import com.mu.bean.Menu;

public interface MenuMapper {

    List<Menu> queryForMenu();

   List<Menu> queryByParentId();
}