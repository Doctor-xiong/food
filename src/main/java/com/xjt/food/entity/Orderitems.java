package com.xjt.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class Orderitems {
    private Long os_id;

    private Long d_id;

    private Float os_allprice;

    private Long os_position;

    private Date os_regtime;
    
    private List<Orderitem> oiList;
}