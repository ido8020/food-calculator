package com.rubypaper.member;

import java.util.List;

public interface MemberService {
	List<MemberVO>  MemberList(MemberVO vo);
	int custno();

	MemberVO edit(MemberVO vo);
	void insert(MemberVO vo);

	  void  update(MemberVO vo);
}
