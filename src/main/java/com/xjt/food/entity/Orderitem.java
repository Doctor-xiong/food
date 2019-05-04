package com.xjt.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Orderitem {
    private Long oi_id;

    private Long os_id;

    private Long m_id;

    private Long oi_num;

    private Float oi_price;

    private Date oi_pegtime;

    private Long oi_position;

    //bean
    private Menu menu;

	public Orderitem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orderitem(Long os_id, Long m_id, Long oi_num, Float oi_price) {
		super();
		this.setOi_id(os_id);
		this.os_id = os_id;
		this.m_id = m_id;
		this.oi_num = oi_num;
		this.oi_price = oi_price;
	}
}