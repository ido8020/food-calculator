package com.rubypaper.guestbook;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestbookDao {
	void insert100(GuestbookVO vo);
	List<GuestbookVO> GuestbookList(GuestbookVO vo);
	int totalcount(GuestbookVO vo);
}
