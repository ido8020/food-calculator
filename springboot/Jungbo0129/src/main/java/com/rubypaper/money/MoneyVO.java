package com.rubypaper.money;

import java.util.Date;

import lombok.Data;

@Data
public class MoneyVO {
	private int custno;
	private int salenol;
	private int pcost;
	private int amount;
	private int price;
	private String pcode; 
	private Date sdate; 
	private String custname;
	private String grade;
	private int money;

}
