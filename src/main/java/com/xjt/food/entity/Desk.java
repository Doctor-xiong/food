package com.xjt.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Desk {
    private Long d_id;

    private String d_password;

    private String d_name;

    private String d_type;

    private String d_place;

    private Long d_position;
}