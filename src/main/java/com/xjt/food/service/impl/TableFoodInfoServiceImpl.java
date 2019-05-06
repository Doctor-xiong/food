package com.xjt.food.service.impl;

import com.xjt.food.entity.TableInfo;
import com.xjt.food.mapper.TableFoodInfoMapper;
import com.xjt.food.service.TableFoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableFoodInfoServiceImpl implements TableFoodInfoService {

    @Autowired
    private TableFoodInfoMapper tableFoodInfoMapper;

    @Override
    public int insert(TableInfo info) {
        return tableFoodInfoMapper.insert(info);
    }

    @Override
    public List<TableInfo> queryAll(Long id) {
        return tableFoodInfoMapper.queryAll(id);
    }
}
