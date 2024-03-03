package com.rubypaper.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
	List<MemberVO>  MemberList(MemberVO vo);
	int custno();
	void insert(MemberVO vo);
	MemberVO edit(MemberVO vo);
	  void  update(MemberVO vo);
}
