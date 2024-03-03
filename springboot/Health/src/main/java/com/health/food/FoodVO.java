package com.health.food;

import lombok.Data;

@Data
public class FoodVO {
	private String foodcd;//음식코드
	private String foodnm;//음식이름
	private String datacd;
	private Double enerc;//칼로리(100g당 모든 영양소도 100g당)
	private Double prot;//단백질
	private Double fatce;//지방
	private Double chocdf; //탄수화물
	private Double sugar;//당분
	private Double nat ; //나트륨
	private Double chole ;//콜레스테롤

	//계산기용
	private int memberidx;
	private int calidx;
	private int amount;
	//검색
	private String ch1;
	private String ch2;
	//페이지 나누기용
	private int start;//현재페이지
	private int end;
	private int nowpage;
	private int pagesize;//페이지당 레코드수
	private int pagelistsize;
	private int totalcount;
	private int totalpage;
}
