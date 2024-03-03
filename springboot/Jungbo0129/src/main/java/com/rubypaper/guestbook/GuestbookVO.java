package com.rubypaper.guestbook;


import java.util.Date;

import lombok.Data;

@Data
public class GuestbookVO {
	private int idx;
	private String name;
	private String memo;
	private String todayStr;
	private Date today;
	//검색용
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
