package com.rubypaper.member;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private int custno;
	private String custname;
	private String phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
	private String joindateStr;
	
	private String postcode;
	private String roadAddress;
	private String detailAddress;
	private String extraAddress;
	private String password;
}
