package com.xjt.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TableInfo {
    private String naem;
    private Long num;
    private Float price;
    private int tableId;
}
