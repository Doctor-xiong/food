package com.hyf.food.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Admin {
    private Long a_id;

    private String a_name;

    private String a_password;
}