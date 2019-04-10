package com.hyf.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Layui  {
	
	private int code = 0;
	private String msg = "";
	private Integer count;
	private List<?> data;
	private String path;
}
 
 

