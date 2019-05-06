package com.xjt.food.action;

import com.xjt.food.entity.TableInfo;
import com.xjt.food.service.TableFoodInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
public class TableFoodInfoAction {

    @Autowired
    private TableFoodInfoService tableFoodInfoService;

    @RequestMapping("insert.action")
    public int insert(TableInfo tableInfo){
        if(ObjectUtils.isEmpty(tableInfo)){
            return 0;
        }
        return tableFoodInfoService.insert(tableInfo);
    }

    @RequestMapping("queryAll.action")
    public int queryAll(Model model ,Long id){
        if(ObjectUtils.isEmpty(id)){
            return 0;
        }
        List<TableInfo> list =  tableFoodInfoService.queryAll(id);
        model.addAttribute("deskFoodList",list);
        return list.size();
    }

}
