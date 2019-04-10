package com.hyf.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Menu {
    private Long m_id;

    private String m_name;

    private String m_img;

    private Float m_price;

    private Long m_num;

    private String m_type;

    private Long m_position;
    
    //临时存放用户选择的菜品数量
    private Long m_number = (long) 0;
}