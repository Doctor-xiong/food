package com.xjt.food.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PageUtils implements Serializable{

    //每页记录数
    private  int limit;
    
    //当前页数
    private  int curr;


    public  int after(){
    	return limit;
    }
    public  int before(){
    	return limit*(curr);
    }
    public  int before1(){
    	return limit*(curr-1);
    }
}
