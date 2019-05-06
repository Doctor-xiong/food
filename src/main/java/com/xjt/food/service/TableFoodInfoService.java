package com.xjt.food.service;

import com.xjt.food.entity.TableInfo;

import java.util.List;

public interface TableFoodInfoService {

    int insert(TableInfo info);

    List<TableInfo> queryAll(Long id);
}
