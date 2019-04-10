package com.hyf.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Expense {
    private Long ex_id;

    private String ex_name;

    private Float ex_price;

    private String ex_other;

    private Date ex_regtime;
}