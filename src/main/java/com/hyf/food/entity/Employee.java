package com.hyf.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Employee {
    private Long e_id;

    private String e_name;

    private Long e_tel;

    private String e_password;

    private String e_address;

    private Date e_regdate;

    private String e_job;

    private Float e_salary;

    private Long e_position;
}